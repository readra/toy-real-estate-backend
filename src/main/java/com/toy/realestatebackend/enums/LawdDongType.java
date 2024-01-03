package com.toy.realestatebackend.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 지역(동) 타입 enum
 *
 * @author 김진용
 */
@Slf4j
@Getter
public enum LawdDongType {
    SEOUL_GANGDONG_JAMSIL (1174000100, "서울특별시 강동구 잠실동");

    // 지역코드(동)
    private final int code;
    // 지역명
    private final String name;

    LawdDongType(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, LawdDongType> codeToEnum = new HashMap<>();

    static {
        Arrays.stream(LawdDongType.values()).forEach(lawdDongType -> codeToEnum.put(lawdDongType.getCode(), lawdDongType));
    }

    /**
     * 지역코드(동) 기준 지역 상세 타입을 반환한다.
     *
     * @param code
     *      지역코드(동)
     * @return
     *      지역 상세 타입
     */
    public static LawdDongType codeOf(final int code) {
        LawdDongType lawdDongType = codeToEnum.get(code);

        Assert.notNull(lawdDongType, "Unsupported lawd dong type code. [CODE]" + code);

        return lawdDongType;
    }
}
