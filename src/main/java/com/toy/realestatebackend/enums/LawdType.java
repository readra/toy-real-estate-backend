package com.toy.realestatebackend.enums;

/**
 * 지역 타입 interface
 *
 * @author 김진용
 */
public interface LawdType {
    /**
     * 지역 코드를 반환한다.
     *
     * @return
     *      지역 코드
     */
    int getCode();

    /**
     * 지역 코드명을 반환한다.
     *
     * @return
     *      지역 코드명
     */
    String getName();
}
