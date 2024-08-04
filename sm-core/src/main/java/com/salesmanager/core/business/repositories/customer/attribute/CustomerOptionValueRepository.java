package com.salesmanager.core.business.repositories.customer.attribute;

import com.salesmanager.core.model.customer.attribute.CustomerOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerOptionValueRepository extends JpaRepository<CustomerOptionValue, Long> {

	
	@Query("select o from CustomerOptionValue o join fetch o.merchantStore om left join fetch o.descriptions od where o.id = :id")
	CustomerOptionValue findOne(@Param("id") Long id);
	
	@Query("select o from CustomerOptionValue o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = :merchantId and o.code = :code")
	CustomerOptionValue findByCode(@Param("merchantId") Integer merchantId, @Param("code") String code);
	
	@Query("select o from CustomerOptionValue o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = :merchantId and od.language.id = :languageId")
	List<CustomerOptionValue> findByStore(@Param("merchantId") Integer merchantId, @Param("languageId") Integer languageId);

}
