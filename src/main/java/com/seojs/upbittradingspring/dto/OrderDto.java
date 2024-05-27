package com.seojs.upbittradingspring.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDto {
    private String uuid;
    private String side;
    private String ordType;
    private Double price;
    private String state;
    private String market;
    private String createdAt;
    private Double volume;
    private Double remainingVolume;
    private Double reservedFee;
    private Double remainingFee;
    private Double paidFee;
    private Double locked;
    private Double executedVolume;
    private Integer tradesCount;
    private String timeInForce;
}
