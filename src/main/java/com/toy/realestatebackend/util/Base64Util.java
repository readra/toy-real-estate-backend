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
    public static String decode(String text) {
        String result = null;

        try {
            result = new String(Base64.getDecoder().decode(text.getBytes()));
        } catch ( Exception e ) {
            log.error("Failed to decode base64.", e);
        }

        return result;
    }
}
