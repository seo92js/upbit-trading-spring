package com.seojs.upbittradingspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seojs.upbittradingspring.dto.AccountDto;
import com.seojs.upbittradingspring.util.Util;
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
public class AccountService {
    private final Util util;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<AccountDto> getAllAccounts() throws JsonProcessingException {
        String authenticationToken = util.generateAuthenticationToken();

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", authenticationToken);
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/accounts", HttpMethod.GET, request, String.class);

        List<AccountDto> accounts = objectMapper.readValue(response.getBody(), new TypeReference<List<AccountDto>>() {});

        return accounts;
    }

    public AccountDto getAccount(String currency) throws JsonProcessingException {

        String authenticationToken = util.generateAuthenticationToken();

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", authenticationToken);
        headers.set("Content-Type", "application/json");

        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(SERVER_URL + "/v1/accounts", HttpMethod.GET, request, String.class);

        List<AccountDto> accounts = objectMapper.readValue(response.getBody(), new TypeReference<List<AccountDto>>() {});

        for(AccountDto account : accounts) {
            if (account.getCurrency().equals(currency)) {
                return account;
            }
        }

        return null;
    }
}
