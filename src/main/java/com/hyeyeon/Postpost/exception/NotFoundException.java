package com.hyeyeon.Postpost.exception;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class NotFoundException extends BusinessException {

    public static final String DEFAULT = "결과가 없습니다.";
    public static final String USER = "검색된 유저가 없습니다.";
    public static final String POST = "글이 존재하지 않습니다.";
    public static final String COMMENT = "댓글이 존재하지 않습니다.";
    public static final String NOTIFICATION = "알림이 존재하지 않습니다.";

    public NotFoundException(String message) {
        super(SC_NOT_FOUND, message);
    }
}
