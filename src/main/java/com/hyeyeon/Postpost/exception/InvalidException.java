package com.hyeyeon.Postpost.exception;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public class InvalidException extends BusinessException {
    public static final String DEFAULT = "올바르지 않은 요청입니다.";
    public static final String USER_NICKNAME = "유효 하지 않은 닉네임 입니다.";

    public InvalidException(String message) {
        super(SC_BAD_REQUEST, message);
    }
}
