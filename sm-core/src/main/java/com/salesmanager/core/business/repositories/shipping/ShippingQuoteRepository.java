package com.salesmanager.core.business.repositories.shipping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.shipping.Quote;
import org.springframework.data.repository.query.Param;

public interface ShippingQuoteRepository extends JpaRepository<Quote, Long> {
	
	
	@Query("select q from Quote as q where q.orderId = :order")
	List<Quote> findByOrder(@Param("order") Long order);

}
