package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleSellerDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private String sellerName;

    public SaleSellerDTO() {
    }

    public SaleSellerDTO(Long id, LocalDate date, double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SaleSellerDTO(Sale entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.amount = entity.getAmount();
        this.sellerName = entity.getSeller().getName();
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
