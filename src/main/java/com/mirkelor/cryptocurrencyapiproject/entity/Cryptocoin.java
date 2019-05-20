package com.mirkelor.cryptocurrencyapiproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@Entity
@Table(name = "cryptocoin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cryptocoin {

    @Column(name = "id")
    @JsonProperty("id")
    private String id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "symbol")
    @JsonProperty("symbol")
    private String symbol;

    @Id
    @Column(name = "coin_rank")
    @JsonProperty("rank")
    private int rank;

    @Column(name = "price_usd")
    @JsonProperty("price_usd")
    private double priceUsd;

    @Column(name = "price_btc")
    @JsonProperty("price_btc")
    private double priceBtc;

    @Column(name = "24h_volume_usd")
    @JsonProperty("24h_volume_usd")
    private double volumeUsd24H;

    @Column(name = "market_cap_usd")
    @JsonProperty("market_cap_usd")
    private double marketCapUsd;

    @Column(name = "available_supply")
    @JsonProperty("available_supply")
    private double availableSupply;

    @Column(name = "total_supply")
    @JsonProperty("total_supply")
    private double totalSupply;

    @Column(name = "max_supply")
    @JsonProperty("max_supply")
    private double maxSupply;

    @Column(name = "percent_change_1h")
    @JsonProperty("percent_change_1h")
    private double percentChange1H;

    @Column(name = "percent_change_24h")
    @JsonProperty("percent_change_24h")
    private double percentChange24H;

    @Column(name = "percent_change_7d")
    @JsonProperty("percent_change_7d")
    private double percentChange7D;


    public Cryptocoin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String  getPriceBtc() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000000000");
        return decimalFormat.format(priceBtc);
    }

    public void setPriceBtc(double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public String getVolumeUsd24H() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 $");
        return decimalFormat.format(volumeUsd24H);
    }

    public void setVolumeUsd24H(double volumeUsd24H) {
        this.volumeUsd24H = volumeUsd24H;
    }

    public String  getMarketCapUsd() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 $");
        return decimalFormat.format(marketCapUsd);
    }

    public void setMarketCapUsd(double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public String getAvailableSupply() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        return decimalFormat.format(availableSupply);
    }

    public void setAvailableSupply(double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        if(decimalFormat.format(maxSupply)!=null){
            return decimalFormat.format(maxSupply);
        } else{
            return "-";
        }
    }

    public void setMaxSupply(double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public double getPercentChange1H() {
        return percentChange1H;
    }

    public void setPercentChange1H(double percentChange1H) {
        this.percentChange1H = percentChange1H;
    }

    public double getPercentChange24H() {
        return percentChange24H;
    }

    public void setPercentChange24H(double percentChange24H) {
        this.percentChange24H = percentChange24H;
    }

    public double getPercentChange7D() {
        return percentChange7D;
    }

    public void setPercentChange7D(double percentChange7D) {
        this.percentChange7D = percentChange7D;
    }

    @Override
    public String toString() {
        return "Cryptocoin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank=" + rank +
                ", priceUsd=" + priceUsd +
                ", priceBtc=" + priceBtc +
                ", volumeUsd24H=" + volumeUsd24H +
                ", marketCapUsd=" + marketCapUsd +
                ", availableSupply=" + availableSupply +
                ", totalSupply=" + totalSupply +
                ", maxSupply=" + maxSupply +
                ", percentChange1H=" + percentChange1H +
                ", percentChange24H=" + percentChange24H +
                ", percentChange7D=" + percentChange7D +
                '}';
    }
}
