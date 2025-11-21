package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public Page<SaleSellerDTO> getSalesReport(String minDate, String maxDate, String sellerName, Pageable pageable) {
        LocalDate maxLocalDate = getMaxLocalDate(maxDate);
        LocalDate minLocalDate = getMinLocalDate(maxLocalDate, minDate);
        Page<Sale> page = repository.getSaleReport(minLocalDate, maxLocalDate, sellerName, pageable);
        return page.map(x -> new SaleSellerDTO(x));
    }

    public List<SaleSummaryDTO> getSalesSummary(String minDate, String maxDate) {
        LocalDate maxLocalDate = getMaxLocalDate(maxDate);
        LocalDate minLocalDate = getMinLocalDate(maxLocalDate, minDate);
        List<SaleSummaryProjection> listProjection = repository.getSalesSummary(minLocalDate, maxLocalDate);
        return listProjection.stream().map(s -> new SaleSummaryDTO(s)).toList();
    }

    public LocalDate getMaxLocalDate(String maxDate) {
        LocalDate maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        if (!maxDate.isBlank()) {
            maxLocalDate = LocalDate.parse(maxDate);
        }
        return maxLocalDate;
    }

    public LocalDate getMinLocalDate(LocalDate maxLocalDate, String minDate) {
        LocalDate minLocalDate = maxLocalDate.minusYears(1L);
        if (!minDate.isBlank()) {
            minLocalDate = LocalDate.parse(minDate);
        }
        return minLocalDate;
    }
}
