package dev.Innocent.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String image;

    @JsonProperty("current_price")
    private double currentPrice;

    @JsonProperty("market_cap")
    private long marketCap;

    @JsonProperty("market_cap_rank")
    private int marketCapRank;

    @JsonProperty("fully_diluted_valuation")
    private long fullyDilutedValuation;

    @JsonProperty("total_volume")
    private long totalVolume;

    @JsonProperty("high_24h")
    private double high24h;

    @JsonProperty("low_24h")
    private double low24h;

    @JsonProperty("price_change_24h")
    private double priceChange24h;

    @JsonProperty("price_change_percentage_24h")
    private double priceChangePercentage24h;

    @JsonProperty("market_cap_change_24h")
    private long marketCapChange24h;

    @JsonProperty("market_cap_change_percentage_24h")
    private double marketCapChangePercentage24h;

    @JsonProperty("circulating_supply")
    private long circulatingSupply;

    @JsonProperty("total_supply")
    private long totalSupply;

    @JsonProperty("max_supply")
    private long maxSupply;

    @JsonProperty("ath")
    private double ath;

    @JsonProperty("ath_change_percentage")
    private double athChangePercentage;

    @JsonProperty("ath_date")
    private Date athDate;

    @JsonProperty("atl")
    private double atl;

    @JsonProperty("atl_change_percentage")
    private double atlChangePercentage;

    @JsonProperty("atl_date")
    private Date atlDate;

    @JsonProperty("roi")
    private double roi;

    @JsonProperty("last_updated")
    private Date lastUpdated;
}
