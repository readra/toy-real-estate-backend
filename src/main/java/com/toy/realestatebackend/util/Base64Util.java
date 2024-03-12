package com.toy.realestatebackend.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

/**
 * Base64 유틸 클래스
 *
 * @author 김진용
 */
@Slf4j
public class Base64Util {
    /**
     * Base64 인코딩 처리한다.
     *
     * @param text
     *      문자열
     * @return
     *      인코딩 문자열
     */
    public static String encode(String text) {
        String result = null;

        try {
            result = Base64.getEncoder().encodeToString(text.getBytes());
        } catch ( Exception e ) {
            log.error("Failed to encode base64. [TEXT]{}", text, e);
        }

        return result;
    }

    /**
     * Base64 디코딩 처리한다.
     *
     * @param text
     *      문자열
     * @return
     *      디코딩 문자열
     */
    public static String decode(String text) {
        String result = null;

        try {
            result = new String(Base64.getDecoder().decode(text.getBytes()));
        } catch ( Exception e ) {
            log.error("Failed to decode base64. [TEXT]{}", text, e);
        }

        return result;
    }
}
