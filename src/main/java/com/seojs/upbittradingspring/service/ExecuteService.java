package com.seojs.upbittradingspring.service;

import com.seojs.upbittradingspring.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Profile("!test")
public class ExecuteService {
    private final AccountService accountService;

    /**
     * 스케줄러 실행
     * @throws IOException
     */
    @Scheduled(fixedDelay = 10000)
    public void execute() throws IOException {
        log.info("--------------------------------");
        List<AccountDto> accounts = accountService.getAllAccounts();

        for(AccountDto accountDto : accounts) {
            log.info("[ " + accountDto.getCurrency() + " ]");
            log.info("수량 : " + accountDto.getBalance());
            log.info("평균가 : " + accountDto.getAvgBuyPrice());
        }
    }
}
