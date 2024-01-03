package com.toy.realestatebackend.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 지역(구) 타입 enum
 *
 * @author 김진용
 */
@Slf4j
@Getter
public enum LawdGuType {
    SEOUL_GANGDONG (11740, "서울특별시 강동구");

    // 지역코드(구)
    private final int code;
    // 지역명
    private final String name;

    LawdGuType(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, LawdGuType> codeToEnum = new HashMap<>();

    static {
        Arrays.stream(LawdGuType.values()).forEach(lawdGuType -> codeToEnum.put(lawdGuType.getCode(), lawdGuType));
    }

    /**
     * 지역코드(구) 기준 지역 타입을 반환한다.
     *
     * @param code
     *      지역코드(구)
     * @return
     *      지역 타입
     */
    public static LawdGuType codeOf(final int code) {
        LawdGuType lawdGuType = codeToEnum.get(code);

        Assert.notNull(lawdGuType, "Unsupported lawd gu type code. [CODE]" + code);

        return lawdGuType;
    }
}
