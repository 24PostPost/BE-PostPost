package com.hyeyeon.Postpost.user.oauth.provider;

import java.util.Map;

public class NaverUserInfo implements Oauth2UserInfo {

    private final Map<?, ?> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<?, ?>) attributes.get("response");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");}
}
