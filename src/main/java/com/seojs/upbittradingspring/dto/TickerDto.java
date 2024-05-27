package com.seojs.upbittradingspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TickerDto {
    private String market;
    private String tradeDate;
    private String tradeTime;
    private String tradeDateKst;
    private String tradeTimeKst;
    private Long tradeTimestamp;
    private Double openingPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double tradePrice;
    private Double prevClosingPrice;
    private String change;
    private Double changePrice;
    private Double changeRate;
    private Double signedChangePrice;
    private Double signedChangeRate;
    private Double tradeVolume;
    private Double accTradePrice;
    @JsonProperty("acc_trade_price_24h")
    private Double accTradePrice24h;
    private Double accTradeVolume;
    @JsonProperty("acc_trade_volume_24h")
    private Double accTradeVolume_24h;
    @JsonProperty("highest_52_week_price")
    private Double highest52WeekPrice;
    @JsonProperty("highest_52_week_date")
    private String highest52WeekDate;
    @JsonProperty("lowest_52_week_price")
    private Double lowest52WeekPrice;
    @JsonProperty("lowest_52_week_date")
    private String lowest52WeekDate;
    private Long timestamp;
}
