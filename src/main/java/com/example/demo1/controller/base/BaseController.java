package com.example.demo1.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo1.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.*;

/**
 * @author ToniR
 * @ClassName: BaseController
 * @date Apr 3, 2019 10:35:21 AM
 * @Description: base
 */
@Slf4j
public class BaseController {

    /**
     * 状态字段名
     */
    public static final String FIELD_STATUS = "status";

    /**
     * 出错消息状态码
     */
    public static final String FIELD_GATEWAY_STATUS = "code";

    /**
     * 出错消息字段名
     */
    public static final String FIELD_MESSAGE = "message";

    /**
     * 返回数据
     */
    public static final String FIELD_DATA = "data";

    /**
     * 返回数据
     */
    public static final String FIELD_USERTYPE = "userType";

    /**
     * 返回总条数
     */
    public static final String COUNT = "count";

    protected static final String REQ_CHARSET = "application/json;charset=UTF-8";

    @Autowired(required = false)
    protected HttpServletRequest request;

    @Autowired(required = false)
    protected HttpServletResponse response;

    protected JSONObject result = new JSONObject();

    protected String requestBody = "";

//    public void initGatewaySuccessResult() {
//        initGatewayResult(GatewayResult.SUCCESS, null, null, 0);
//    }
//
//    public void initGatewayErrorResult(String message) {
//        initGatewayResult(GatewayResult.ERROR, message, null, 0);
//    }
//
//    public void initGatewayErrorResult(GatewayResult gatewayResult, String message) {
//        initGatewayResult(gatewayResult, message, null, 0);
//    }
//
//    public void initGatewayErrorResult(GatewayResult gatewayResult) {
//        initGatewayResult(gatewayResult, null, null, 0);
//    }
//
//    private void initGatewayResult(GatewayResult status, String message, Object data, int count) {
//        response.setHeader("Content-Type", REQ_CHARSET);
//        String statusCode = "1";
//        if (!statusCode.equals(status.status)) {
//            statusCode = "0";
//        }
//        result.put(FIELD_STATUS, statusCode);
//        result.put(FIELD_GATEWAY_STATUS, statusCode);
//        result.put(FIELD_MESSAGE, StringUtils.isEmpty(message) ? status.msg : message);
//        if (data != null) {
//            result.put(FIELD_DATA, data);
//        }
//        if (count != 0) {
//            result.put(COUNT, count);
//        }
//    }

    public void addData(Object data) {
        result.put(FIELD_DATA, data);
    }

    public void addCount(long count) {
        result.put(COUNT, count);
    }

    public void addData(List<?> data) {
        result.put(FIELD_DATA, data);
    }

    public void addUserType(int userType) {
        result.put(FIELD_USERTYPE, userType);
    }

    public void addData(String key, Object val) {
        result.put(key, val);
    }

    /**
     * 从request获取整型参数
     *
     * @param param 参数名
     * @param def   默认值
     * @return
     */
    public int getInt(String param, int def) {
        String val = getString(param, "");
        if (StringUtils.isEmpty(val)) {
            return def;
        }
        return StringUtils.toInt(val, def);
    }

    /**
     * 从request获取字符串型参数
     *
     * @param param 参数名
     * @param def   默认值
     * @return
     */
    public String getString(String param, String def) {
        String val = request.getParameter(param);
        return StringUtils.isEmpty(val) ? def : val;
    }

    public Long getLong(String param, long def) {
        String val = request.getParameter(param);
        return StringUtils.toLong(val, def);
    }

    public String getAttr(String attr) {
        String val = (String) request.getAttribute(attr);
        return val == null ? "" : val;
    }

    public String getRequestBody() {
        if (!StringUtils.isEmpty(this.requestBody)) {
            return this.requestBody;
        }

        StringBuffer sb = new StringBuffer();
        try (BufferedReader reader = request.getReader()) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            this.requestBody = sb.toString();
            return requestBody;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return "";
        }
    }

    /**
     * 从request获取浮点型参数
     *
     * @param param 参数名
     * @param def   默认值
     * @return
     */
    public double getDouble(String param, double def) {
        String val = getString(param, "");
        if (StringUtils.isEmpty(val)) {
            return def;
        }

        return StringUtils.toDouble(val, def);
    }

    /**
     * 从request中获取数组型参数
     *
     * @param param 参数名
     * @return 如果没有该参数的值，返回长度为0的字符串数组
     */
    public String[] getArray(String param) {
        String[] val = request.getParameterValues(param);
        return val == null ? new String[0] : val;
    }

    public Long getLong(String param, Long def) {
        String val = getString(param, "");
        if (StringUtils.isEmpty(param)) {
            return def;
        }

        return StringUtils.toLong(val, def);
    }

//    public Date getDate(String param, Date def) {
//
//        String val = getString(param, "");
//        if (StrUtils.empty(val)) {
//            return def;
//        }
//
//        Date d = DateUtils.parse(val);
//        return d == null ? def : d;
//    }

    /**
     * 从request中获取一个bean
     *
     * @param T
     * @return
     */
//    public <T> T getBean(Class<T> t) {
//        return getBean(t, "");
//    }

    /**
     * 从request中获取一个bean
     * <p>
     *
     * @param T      Bean的类型
     * @param prefix 前缀,Bean的属性值从 prefix开头的参数中获取
     * @return
     */
//    public <T> T getBean(Class<T> t, String prefix) {
//        // 如果实例化对象出错，返回null
//        Object obj;
//        try {
//            obj = t.newInstance();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return null;
//        }
//
//        // 遍历Bean的所有属性
//        Field[] fields = t.getDeclaredFields();
//        for (Field f : fields) {
//            String name = prefix + f.getName();
//            String value = getString(name, "");
//            if (StringUtils.isEmpty(value)) {
//                continue;
//            }
//
//            try {
//                BeanUtils.setProperty(obj, name, value);
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//        return JSON.parseObject(String.valueOf(obj), t);
//    }

    /**
     * @return T
     * @author ToniR
     * @title getRequestBean
     * @description 接收请求对象封装
     * @since Apr 5, 2019 11:21:40 PM
     */
//    public <T extends Serializable> T getRequestBean(Class<T> clazz) {
//        if (request == null) {
//            throw new IllegalArgumentException("FormBeanUtil.get中的request为空");
//        }
//
//        T obj = null;
//        Map<String, String[]> parameterMap = null;
//        Map<String, String> params = new HashMap<String, String>(16);
//        try {
//            obj = clazz.newInstance();
//            parameterMap = request.getParameterMap();
//            Iterator<Entry<String, String[]>> it = parameterMap.entrySet().iterator();
//            while (it.hasNext()) {
//                Entry<String, String[]> next = it.next();
//                String[] values = next.getValue();
//                String valueStr = "";
//                for (int i = 0; i < values.length; i++) {
//                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//                }
//                if (StringUtils.isEmpty(valueStr)) {
//                    continue;
//                } else {
//                    params.put(next.getKey(), valueStr);
//                }
//            }
//            log.info("className: {}, receive: {}", this.getClass().getSimpleName(), params);
//            BeanUtils.populate(obj, params);
//        } catch (Exception e) {
//            log.error("className: {}, exception输出为: ", this.getClass().getSimpleName(), e);
//        }
//        return obj;
//    }

    /**
     * @return String
     * @author ToniR
     * @title returnResponse
     * @description 返回值响应
     * @since Apr 5, 2019 11:21:29 PM
     */
    protected String returnResponse(Object object) {
        String result = JSON.toJSONString(object, SerializerFeature.WriteNullStringAsEmpty);
        log.info("接口返回返回结果: {}", result);
        return result;
    }

    protected String returnResponse(Object object, SerializerFeature... features) {
        String result = JSON.toJSONString(object, features);
        log.info("接口返回返回结果: {}", result);
        return result;
    }

//    public void writeResponse(HttpServletResponse response, ImageData image) throws Exception {
//        response.setContentType(image.getContentType());
//        //设置浏览器缓存
//        response.setHeader("Cache-Control", "max-age=31536000");
//        OutputStream out = response.getOutputStream();
//        out.write(image.getData());
//        out.flush();
//        out.close();
//    }
}
