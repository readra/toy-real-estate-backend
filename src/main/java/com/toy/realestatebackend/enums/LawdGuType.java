package com.toy.realestatebackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.toy.realestatebackend.enums.LawdSiType.*;

/**
 * 지역(구) 타입 enum
 *
 * @author 김진용
 */
@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LawdGuType {
    GANGDONG (11740, "강동구", SEOUL);

    // 지역코드(구)
    private final int code;
    // 지역명
    private final String name;
    // 지역(시)
    private final LawdSiType lawdSiType;

    LawdGuType(final int code, final String name, final LawdSiType lawdSiType) {
        this.code = code;
        this.name = name;
        this.lawdSiType = lawdSiType;
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
    @JsonCreator
    public static LawdGuType codeOf(@JsonProperty("code") final int code) {
        LawdGuType lawdGuType = codeToEnum.get(code);

        Assert.notNull(lawdGuType, "Unsupported lawd gu type code. [CODE]" + code);

        return lawdGuType;
    }
}
