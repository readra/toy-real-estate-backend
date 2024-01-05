package com.toy.realestatebackend.controller;

import com.toy.realestatebackend.enums.LawdGuType;
import com.toy.realestatebackend.enums.LawdSiType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 지역 Controller Layer
 *
 * @author 김진용
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LawdController {
    /**
     * 지역(시) 목록 API
     *
     * @return
     *      지역(시) 목록
     */
    @GetMapping("/api/lawd-si")
    public List<LawdSiType> lawdSi() {
        return Arrays.asList(LawdSiType.values());
    }

    /**
     * 지역(구) 목록 API
     *
     * @return
     *      지역(구) 목록
     */
    @GetMapping("/api/lawd-gu")
    public List<LawdGuType> lawdGu() {
        return Arrays.asList(LawdGuType.values());
    }
}
