package com.toy.realestatebackend.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 지역 타입 enum
 *
 * @author 김진용
 */
@Slf4j
@Getter
public enum LawdType {
    SEOUL_GANGDONGGU (11740, "서울특별시 강동구");

    // 지역코드
    private final int code;
    // 지역명
    private final String name;

    LawdType(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, LawdType> codeToEnum = new HashMap<>();

    static {
        for ( LawdType lawdType : LawdType.values() ) {
            codeToEnum.put(lawdType.getCode(), lawdType);
        }
    }

    /**
     * 지역코드 기준 지역 타입을 반환한다.
     *
     * @param code
     *      지역코드
     * @return
     *      지역 타입
     */
    public static LawdType codeOf(final int code) {
        LawdType lawdType = codeToEnum.get(code);

        Assert.notNull(lawdType, "Unsupported legal building type code. [CODE]" + code);

        return lawdType;
    }
}
