package com.demo.stocks.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "stocks")
public class Stock {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String symbol;

    private String exchange;

    private String industry;

    private String company;

    private Date listingDate;

    @Id
    private int id;

}
