package com.ccx.starter.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xcc
 * @Title:
 * @Package
 * @Description: 用户自定义密钥
 * @date 2021/12/23 17:19
 */
@ConfigurationProperties(prefix = "spring.encrypt")
@Component
public class EncryptProperties {


    private final static String DEFAULT_KEY = "xcc";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
