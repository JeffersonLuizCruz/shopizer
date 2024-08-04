package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerAttribute;
import org.springframework.data.repository.query.Param;

public interface CustomerAttributeRepository extends JpaRepository<CustomerAttribute, Long> {

	
	@Query("select a from CustomerAttribute a left join fetch a.customerOption aco left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where a.id = :id")
	CustomerAttribute findOne(@Param("id") Long id);
	
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = :merchantId and ac.id = :customerId and aco.id = :id")
	CustomerAttribute findByOptionId(@Param("merchantId") Integer merchantId, @Param("customerId") Long customerId, @Param("id") Long id);
	
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = :merchantId and aco.id = :id")
	List<CustomerAttribute> findByOptionId(@Param("merchantId") Integer merchantId, @Param("id") Long id);

	@Query("select distinct a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = :merchantId and ac.id = :customerId")
	List<CustomerAttribute> findByCustomerId(@Param("merchantId") Integer merchantId, @Param("customerId") Long customerId);
	
	@Query("select a from CustomerAttribute a join fetch a.customer ac left join fetch a.customerOption aco join fetch aco.merchantStore acom left join fetch a.customerOptionValue acov left join fetch aco.descriptions acod left join fetch acov.descriptions acovd where acom.id = :merchantId and acov.id = :id")
	List<CustomerAttribute> findByOptionValueId(@Param("merchantId") Integer merchantId, @Param("id") Long id);
}
