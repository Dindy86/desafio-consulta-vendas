package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDTO {
    private String sellerName;
    private Double total;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryDTO(SaleSummaryProjection projection) {
        this.sellerName = projection.getName();
        this.total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
