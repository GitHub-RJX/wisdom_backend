package com.rjx.ucenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstanWxtiesUtil implements InitializingBean {
    @Value("wxed9954c01bb89b47")
    private String appId;
    @Value("a7482517235173ddb4083788de60b90e")
    private String appSecret;
    @Value("http://guli.shop/api/ucenter/wx/callback")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }
}
