package com.salesmanager.core.business.repositories.customer.optin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.system.optin.CustomerOptin;
import org.springframework.data.repository.query.Param;

public interface CustomerOptinRepository extends JpaRepository<CustomerOptin, Long> {

	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = :storeId and o.code = :code")
	List<CustomerOptin> findByCode(@Param("storeId") Integer storeId, @Param("code") String code);
	
	@Query("select distinct c from CustomerOptin as c left join fetch c.optin o join fetch o.merchant om where om.id = :storeId and o.code = :code and c.email = :email")
	CustomerOptin findByMerchantAndCodeAndEmail(@Param("storeId") Integer storeId, @Param("code") String code, @Param("email") String email);
}
