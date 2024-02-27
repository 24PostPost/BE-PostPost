package com.hyeyeon.Postpost.exception;

import static jakarta.servlet.http.HttpServletResponse.SC_CONFLICT;

public class ConflictException extends BusinessException {

    public static final String MEMBER = "중복된 닉네임 입니다.";

    public ConflictException(String message) {
        super(SC_CONFLICT, message);
    }
}
