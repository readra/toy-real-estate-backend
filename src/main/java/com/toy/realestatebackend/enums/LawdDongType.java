package com.toy.realestatebackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.toy.realestatebackend.enums.LawdGuType.GANGDONG;

/**
 * 지역(동) 타입 enum
 *
 * @author 김진용
 */
@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LawdDongType implements LawdType {
    JAMSIL (1174000100, "잠실동", GANGDONG);

    // 지역코드(동)
    private final int code;
    // 지역명
    private final String name;
    // 지역(구)
    private final LawdGuType lawdGuType;

    LawdDongType(final int code, final String name, final LawdGuType lawdGuType) {
        this.code = code;
        this.name = name;
        this.lawdGuType = lawdGuType;
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
    @JsonCreator
    public static LawdDongType codeOf(@JsonProperty("code") final int code) {
        LawdDongType lawdDongType = codeToEnum.get(code);

        if ( null == lawdDongType ) {
            log.warn("Unsupported lawd dong type code. [CODE]" + code);
        }

        return lawdDongType;
    }
}
