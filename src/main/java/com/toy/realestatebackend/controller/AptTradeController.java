package com.toy.realestatebackend.controller;

import com.toy.realestatebackend.domain.AptTradeItem;
import com.toy.realestatebackend.domain.AptTradeSearchCondition;
import com.toy.realestatebackend.service.AptTradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 아파트매매실거래 Controller Layer
 *
 * @author 김진용
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AptTradeController {
    private final AptTradeService aptTradeService;

    public AptTradeController(final AptTradeService aptTradeService) {
        this.aptTradeService = aptTradeService;
    }

    /**
     * 아파트매매실거래 목록 API
     *
     * @return
     *      아파트매매실거래 목록
     */
    @GetMapping("/api/apartment")
    public List<AptTradeItem> findAptTradeItems(AptTradeSearchCondition aptTradeSearchCondition) {
        return aptTradeService.findAptTradeItems(aptTradeSearchCondition);
    }
}
