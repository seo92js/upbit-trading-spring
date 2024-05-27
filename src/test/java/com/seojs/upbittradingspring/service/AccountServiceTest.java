package com.seojs.upbittradingspring.service;

import com.seojs.upbittradingspring.dto.AccountDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    void 계좌조회() throws IOException {
        List<AccountDto> accounts = accountService.getAllAccounts();

        System.out.println(accounts.size());

        AccountDto account = accountService.getAccount("XRP");

        System.out.println(account.getBalance());

        Assertions.assertThat(account).isNotNull();
    }
}