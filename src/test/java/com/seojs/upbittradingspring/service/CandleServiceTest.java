package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seojs.upbittradingspring.dto.CandleDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CandleServiceTest {
    @Autowired
    CandleService candleService;

    @Test
    void 캔들조회() throws JsonProcessingException {
        List<CandleDto> candles = candleService.getMinCandles(5, "KRW-XRP", 100);

        for (CandleDto candle : candles) {
            System.out.println(candle.getLowPrice());
        }

        Assertions.assertThat(candles).isNotNull();
    }
}