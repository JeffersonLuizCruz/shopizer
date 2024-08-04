package com.salesmanager.core.business.repositories.customer.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.attribute.CustomerOption;
import org.springframework.data.repository.query.Param;

public interface CustomerOptionRepository extends JpaRepository<CustomerOption, Long> {

	
	@Query("select o from CustomerOption o join fetch o.merchantStore om left join fetch o.descriptions od where o.id = :id")
	CustomerOption findOne(@Param("id") Long id);
	
	@Query("select o from CustomerOption o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = :merchantId and o.code = :code")
	CustomerOption findByCode(@Param("merchantId") Integer merchantId, @Param("code") String code);
	
	@Query("select o from CustomerOption o join fetch o.merchantStore om left join fetch o.descriptions od where om.id = :merchantId and od.language.id = :languageId")
	List<CustomerOption> findByStore(@Param("merchantId") Integer merchantId, @Param("languageId") Integer languageId);

}
