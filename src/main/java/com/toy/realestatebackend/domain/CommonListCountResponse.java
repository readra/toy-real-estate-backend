package com.toy.realestatebackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 공통 목록 + 카운트 결과 클래스
 *
 * @param <T>
 *     타입
 *
 * @author 김진용
 */
@Getter
@ToString
@NoArgsConstructor
public class CommonListCountResponse<T> {
    // 결과 목록
    private List<T> results;
    // 결과 갯수
    private int totalCount;

    /**
     * 생성자
     *
     * @param results
     *      결과 목록
     * @param totalCount
     *      결과 갯수
     */
    @Builder
    public CommonListCountResponse(List<T> results, int totalCount) {
        this.results = results;
        this.totalCount = totalCount;
    }
}
