package com.hyeyeon.Postpost.user.oauth;

import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import com.hyeyeon.Postpost.user.oauth.provider.GoogleUserInfo;
import com.hyeyeon.Postpost.user.oauth.provider.NaverUserInfo;
import com.hyeyeon.Postpost.user.oauth.provider.Oauth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oauth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
            System.out.printf("네이버 로그인 요청");
            oauth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("우리 앱은 구글, 네이버 로그인만 지원해요!!");
        }

        String provider = oauth2UserInfo.getProvider(); // google
        String providerId = oauth2UserInfo.getProviderId();
        String name = provider + "_" + providerId;
        String email = oauth2UserInfo.getEmail();
        String role = "USER";

        User userEntity = userRepository.findByName(name);
        if (userEntity == null) {
            userEntity = User.builder()
                    .name(name)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        }

        return new UserDetailsCustom(userEntity, oAuth2User.getAttributes());
    }
}