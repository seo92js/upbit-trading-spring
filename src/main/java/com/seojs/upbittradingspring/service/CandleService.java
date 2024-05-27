package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.upbittradingspring.dto.CandleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.seojs.upbittradingspring.config.Config.SERVER_URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class CandleService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    public List<CandleDto> getMinCandles(int min, String market, int count) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();

        headers.set("accept", "application/json");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/candles/minutes/" + min + "?market=" + market + "&count=" + count, HttpMethod.GET, request, String.class);

        List<CandleDto> candles = objectMapper.readValue(response.getBody(), new TypeReference<List<CandleDto>>() {});

        return candles;
    }
}
