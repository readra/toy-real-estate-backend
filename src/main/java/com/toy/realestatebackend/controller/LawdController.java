package com.toy.realestatebackend.controller;

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
    @GetMapping("/api/lawd-si")
    public List<LawdSiType> lawdSi() {
        return Arrays.asList(LawdSiType.values());
    }
}
