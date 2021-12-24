package com.ccx.starter.resp;

import com.ccx.starter.anno.Encrypt;
import com.ccx.starter.model.RespBean;
import com.ccx.starter.prop.EncryptProperties;
import com.ccx.starter.utils.AESUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author xcc
 * @Title: 加密接口
 * @Package
 * @Description:
 * @date 2021/12/23 17:28
 */
@ControllerAdvice
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptResponse implements ResponseBodyAdvice<RespBean> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    EncryptProperties encryptProperties;


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public RespBean beforeBodyWrite(RespBean body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final byte[] keyBytes = encryptProperties.getKey().getBytes();
        try {
            if (body.getMsg() != null) {
                body.setMsg(AESUtils.encrypt(body.getMsg().getBytes(), keyBytes));
            }
            if (body.getObj() != null) {
                body.setObj(AESUtils.encrypt(objectMapper.writeValueAsBytes(body.getObj()), keyBytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
