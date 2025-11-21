package com.devsuperior.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(
            value = "SELECT s FROM Sale s JOIN FETCH s.seller sl " + "WHERE s.date BETWEEN :minDate AND :maxDate AND UPPER(sl.name) LIKE CONCAT('%',UPPER(:sellerName),'%')",

            countQuery = "SELECT COUNT(s) FROM Sale s JOIN s.seller sl " + "WHERE s.date BETWEEN :minDate AND :maxDate AND UPPER(sl.name) LIKE CONCAT('%',UPPER(:sellerName),'%')"
    )
    Page<Sale> getSaleReport(LocalDate minDate, LocalDate maxDate, String sellerName, Pageable pageable);

    @Query(" SELECT s.seller.name as name, SUM(s.amount) as total FROM Sale s" + "    WHERE s.date between :minDate AND :maxDate" + "    GROUP BY s.seller.name")
    List<SaleSummaryProjection> getSalesSummary(LocalDate minDate, LocalDate maxDate);
}
