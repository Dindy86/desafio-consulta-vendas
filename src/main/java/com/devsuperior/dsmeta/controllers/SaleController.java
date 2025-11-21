package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleSellerDTO>> getReport(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable
    ) {
        // TODO
        Page<SaleSellerDTO> page = service.getSalesReport(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleSummaryDTO>> getSummary(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate
    ) {
        // TODO
        List<SaleSummaryDTO> summary = service.getSalesSummary(minDate, maxDate);
        return ResponseEntity.ok(summary);
    }
}
