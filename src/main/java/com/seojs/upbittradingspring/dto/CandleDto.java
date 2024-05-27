package com.seojs.upbittradingspring.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CandleDto {
    private String market;
    private String candleDateTimeUtc;
    private String candleDateTimeKst;
    private Double openingPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double tradePrice;
    private Long timestamp;
    private Double candleAccTradePrice;
    private Double candleAccTradeVolume;
    private Integer unit;
}
