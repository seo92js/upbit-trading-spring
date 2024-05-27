package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.seojs.upbittradingspring.config.Config;
import com.seojs.upbittradingspring.dto.OrderDto;
import com.seojs.upbittradingspring.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.seojs.upbittradingspring.config.Config.SERVER_URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final Util util;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    public OrderDto bid(String market, String price) throws NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException {

        if(Double.parseDouble(price) < Config.MIN_ORDER_AMOUNT) {
            log.info("최소 주문 금액은 " + Config.MIN_ORDER_AMOUNT + " 입니다.");
            return null;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("market", market);
        params.put("side", "bid");
        params.put("price", price);
        params.put("ord_type", "price");

        ArrayList<String> queryElements = new ArrayList<>();

        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");

        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        String authenticationToken = util.generateAuthenticationToken(queryHash, "SHA512");

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", authenticationToken);
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity(new Gson().toJson(params), headers);

        ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/orders", HttpMethod.POST, request, String.class);

        OrderDto orderDto = objectMapper.readValue(response.getBody(), new TypeReference<OrderDto>() {});

        log.info("[" + market + "] 매수, 금액 : " + price);

        return orderDto;
    }

    public OrderDto ask(String market, String volume) throws NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException {
        Double balance = accountService.getAccount(market).getBalance();

        if(balance < Double.parseDouble(volume)) {
            log.info("매도 요청 수량이 보유 수량보다 많습니다. 보유 수량 : " + balance + ", 매도 요청 수량 : " + volume);
            return null;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("market", market);
        params.put("side", "ask");
        params.put("ord_type", "market");
        params.put("volume", volume);

        ArrayList<String> queryElements = new ArrayList<>();

        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");

        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        String authenticationToken = util.generateAuthenticationToken(queryHash, "SHA512");

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", authenticationToken);
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity(new Gson().toJson(params), headers);

        ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/orders", HttpMethod.POST, request, String.class);

        OrderDto orderDto = objectMapper.readValue(response.getBody(), new TypeReference<OrderDto>() {});

        log.info("[" + market + "] : 매도, 수량 : " + volume);

        return orderDto;
    }
}
