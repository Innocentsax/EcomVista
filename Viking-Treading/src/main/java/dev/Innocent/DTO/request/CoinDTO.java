package dev.Innocent.DTO.request;

import lombok.Data;

import java.util.Date;

@Data
public class CoinDTO {
    private String id;
    private String name;
    private String symbol;
    private String image;
    private double current_price;
    private double market_cap;
    private double market_cap_rank;
    private double total_volume;
    private double high_24h;
    private double low_24h;
    private double price_change_24h;
    private double price_change_percentage_24h;
    private double market_cap_change_24h;
    private double market_cap_change_percentage_24h;
    private double circulating_supply;
    private double total_supply;
    private long ath;
    private long ath_change_percentage;
    private Date ath_date;
    private long alt_Change_Percentage;
    private Date last_updated;
    private Date alt_date;
}
