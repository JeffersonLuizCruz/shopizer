package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerOptionSet;
import org.springframework.data.repository.query.Param;

public interface CustomerOptionSetRepository extends JpaRepository<CustomerOptionSet, Long> {

	
	@Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where c.id = :id")
	CustomerOptionSet findOne(@Param("id") Long id);
	
	@Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where com.id = :merchantStoreId and co.id = :id")
	List<CustomerOptionSet> findByOptionId(@Param("merchantStoreId") Integer merchantStoreId, @Param("id") Long id);
	
	@Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where com.id = :merchantStoreId and cov.id = :id")
	List<CustomerOptionSet> findByOptionValueId(@Param("merchantStoreId") Integer merchantStoreId, @Param("id") Long id);
	
	@Query("select c from CustomerOptionSet c join fetch c.customerOption co join fetch c.customerOptionValue cov join fetch co.merchantStore com left join fetch co.descriptions cod left join fetch cov.descriptions covd where com.id = :merchantStoreId and cod.language.id = :languageId and covd.language.id = :languageId order by c.sortOrder asc")
	List<CustomerOptionSet> findByStore(@Param("merchantStoreId") Integer merchantStoreId, @Param("languageId") Integer languageId);

}
