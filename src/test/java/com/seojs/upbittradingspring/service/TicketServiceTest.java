package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seojs.upbittradingspring.dto.TickerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TicketServiceTest {
    @Autowired
    TicketService ticketService;

    @Test
    void 가격조회() throws JsonProcessingException {
        List<String> markets = Arrays.asList("KRW-BTT", "KRW-XRP");

        List<TickerDto> tickerDtos = ticketService.getPrices(markets);

        System.out.println(tickerDtos.get(0).getTradePrice());

        Assertions.assertThat(tickerDtos).isNotNull();

        TickerDto tickerDto = ticketService.getPrice("KRW-XRP");

        System.out.println(tickerDto.getTradePrice());

        Assertions.assertThat(tickerDto).isNotNull();
    }
}