package com.example.demo1.utils;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

/**
 * sftp 工具类
 *
 * @Author: chenping
 * @Date: 2019/11/17
 */
@Slf4j
public class SftpUtil {
    private String sftpIp;
    private String sftpUsername;
    private String sftpPassword;
    private int sftpPort;
    private ChannelSftp sftp = null;
    private Session session = null;

    /**
     * @Description: SftpUtil 构造方法
     * @param: sftpIp
     * @param: sftpUsername
     * @param: sftpPassword
     * @param: sftpPort
     * @return:
     * @Author: chenping
     * @Date: 2019/11/17 23:58
     */
    public SftpUtil(String sftpIp, String sftpUsername, String sftpPassword, int sftpPort) {
        this.sftpIp = sftpIp;
        this.sftpUsername = sftpUsername;
        this.sftpPassword = sftpPassword;
        this.sftpPort = sftpPort;
    }

    /**
     * @Description:  连接sftp
     * @param:
     * @return: com.jcraft.jsch.ChannelSftp
     * @Author: chenping
     * @Date: 2019/11/18
     */
    public ChannelSftp connect() throws Exception {
        JSch jsch = new JSch();
        session = jsch.getSession(sftpUsername, sftpIp, sftpPort);
        session.setPassword(sftpPassword);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.connect();
        Channel channel = session.openChannel("sftp");
        if (channel != null) {
            channel.connect();
        } else {
            log.info("channel connecting failed.");
            throw new Exception("channel connecting failed.");
        }
        sftp = (ChannelSftp) channel;
        return sftp;
    }

    /**
     * @Description: sftp关闭
     * @Param: []
     * @return: void
     * @Author: chenping
     * @Date: 2019/11/17
     */
    public void close() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                log.info("sftp is closed already");
            }
        }
        if (this.session != null) {
            if (this.session.isConnected()) {
                this.session.disconnect();
                log.info("sshSession is closed already");
            }
        }
    }

    /**
     * @Description:  uploadFile
     * @param: remotePath 上传路径
     * @param: remoteFileName 上传文件名
     * @param: localPath    本地路径
     * @param: localFileName    本地要上传的文件名
     * @return: boolean
     * @Author: chenping
     * @Date: 2019/11/18
     */
    public boolean uploadFile(String remotePath, String remoteFileName,
                              String localPath, String localFileName) {
        FileInputStream in = null;
        try {
            log.info(remotePath);
            createDir(remotePath);//若是没有路径就创建文件夹
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            System.out.println(in);
            sftp.put(in, remoteFileName);
            System.out.println(sftp);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * @Description: bacthUploadFile 批量上传文件；上传某路径下的文件
     * @param: remotePath
     * @param: localPath
     * @return: boolean
     * @Author: chenping
     * @Date: 2019/11/18
     */
    public boolean bacthUploadFile(String remotePath, String localPath) {
        try {
            File file = new File(localPath);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()
                        && files[i].getName().indexOf("bak") == -1) {
                    synchronized (remotePath) {
                        uploadFile(remotePath, files[i].getName(), localPath, files[i].getName());
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return false;

    }

    /**
     * @Description:  downloadFile
     * @param: remotePath
     * @param: remoteFileName
     * @param: localPath
     * @param: localFileName
     * @return: boolean
     * @Author: chenping
     * @Date: 2019/11/18
     */
    public boolean downloadFile(String remotePath, String remoteFileName,
                                String localPath, String localFileName) {
        try {
            sftp.cd(remotePath);
            File file = new File(localPath + localFileName);
            sftp.get(remoteFileName, new FileOutputStream(file));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                this.sftp.cd(createpath);
                log.info(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                createpath = filePath.toString();
                if (isDirExist(createpath)) {
                    sftp.cd(createpath);
                } else {
                    sftp.mkdir(createpath);
                    sftp.cd(createpath);
                }
            }
            this.sftp.cd(createpath);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

}
