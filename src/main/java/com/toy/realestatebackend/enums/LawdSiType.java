package com.toy.realestatebackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 지역(시) 타입 enum
 *
 * @author 김진용
 */
@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LawdSiType implements LawdType {
    SEOUL (11, "서울특별시");

    // 지역코드(시)
    private final int code;
    // 지역명
    private final String name;

    LawdSiType(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<Integer, LawdSiType> codeToEnum = new HashMap<>();

    static {
        Arrays.stream(LawdSiType.values()).forEach(lawdSiType -> codeToEnum.put(lawdSiType.getCode(), lawdSiType));
    }

    /**
     * 지역코드(시) 기준 지역 상세 타입을 반환한다.
     *
     * @param code
     *      지역코드(시)
     * @return
     *      지역 상세 타입
     */
    @JsonCreator
    public static LawdSiType codeOf(@JsonProperty("code") final int code) {
        LawdSiType lawdSiType = codeToEnum.get(code);

        if ( null == lawdSiType ) {
            log.warn("Unsupported lawd si type code. [CODE]" + code);
        }

        return lawdSiType;
    }
}
