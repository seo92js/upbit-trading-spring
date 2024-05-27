package com.seojs.upbittradingspring.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@NoArgsConstructor
@Configuration
public class Config {

    @Value("${UPBIT_OPEN_API_ACCESS_KEY}")
    private String ACCESS_KEY;

    @Value("${UPBIT_OPEN_API_SECRET_KEY}")
    private String SECRET_KEY;

    public static final String SERVER_URL = "https://api.upbit.com";

    public static final double MIN_ORDER_AMOUNT = 5000.0;
}
