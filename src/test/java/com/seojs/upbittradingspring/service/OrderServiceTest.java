package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seojs.upbittradingspring.dto.OrderDto;
import com.seojs.upbittradingspring.dto.TickerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;

    @Autowired
    TicketService ticketService;

    @Test
    void 최소주문가격_아래_매수() throws UnsupportedEncodingException, NoSuchAlgorithmException, JsonProcessingException {
        TickerDto tickerDto = ticketService.getPrice("KRW-XRP");

        System.out.println(tickerDto.getTradePrice());

        OrderDto orderDto = orderService.bid("KRW-XRP", "4000.0");

        Assertions.assertThat(orderDto).isNull();
    }

    @Test
    void 보유수량_오버_매도() throws UnsupportedEncodingException, NoSuchAlgorithmException, JsonProcessingException {
        OrderDto orderDto = orderService.ask("XRP", "555555555555555555.1");

        Assertions.assertThat(orderDto).isNull();
    }

}