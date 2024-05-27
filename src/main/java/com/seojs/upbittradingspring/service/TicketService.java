package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.upbittradingspring.dto.TickerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.seojs.upbittradingspring.config.Config.SERVER_URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public TickerDto getPrice(String market) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();

        headers.set("accept", "application/json");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/ticker?markets=" + market, HttpMethod.GET, request, String.class);

        List<TickerDto> tickerDtos = objectMapper.readValue(response.getBody(), new TypeReference<List<TickerDto>>() {});

        return tickerDtos.get(0);
    }

    public List<TickerDto> getPrices(List<String> markets) throws JsonProcessingException {
        List<TickerDto> tickerDtos = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();

        headers.set("accept", "application/json");

        HttpEntity request = new HttpEntity(headers);

        for(String market : markets) {
            ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/ticker?markets=" + market, HttpMethod.GET, request, String.class);

            TickerDto tickerDto = objectMapper.readValue(response.getBody(), new TypeReference<List<TickerDto>>() {}).get(0);

            tickerDtos.add(tickerDto);
        }

        return tickerDtos;
    }
}
