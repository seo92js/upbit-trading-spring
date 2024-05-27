package com.seojs.upbittradingspring.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountDto {
    private String currency;
    private Double balance;
    private Double locked;
    private Double avgBuyPrice;
    private Boolean avgBuyPriceModified;
    private String unitCurrency;
}
