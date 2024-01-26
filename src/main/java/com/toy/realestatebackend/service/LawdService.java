package com.toy.realestatebackend.service;

import com.toy.realestatebackend.enums.LawdDongType;
import com.toy.realestatebackend.enums.LawdGuType;
import com.toy.realestatebackend.enums.LawdSiType;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 지역 Service Layer
 *
 * @author 김진용
 */
@Service
public class LawdService {
    /**
     * 지역(시) 목록 조회
     *
     * @return
     *      지역(시) 목록
     */
    public List<LawdSiType> findLawdSis() {
        return Arrays.asList(LawdSiType.values());
    }

    /**
     * 지역(구) 목록
     *
     * @return
     *      지역(구) 목록
     */
    public List<LawdGuType> findLawdGus() {
        return Arrays.asList(LawdGuType.values());
    }

    /**
     * 지역(동) 목록
     *
     * @return
     *      지역(동) 목록
     */
    public List<LawdDongType> findLawdDongs() {
        return Arrays.asList(LawdDongType.values());
    }
}
