package com.salesmanager.core.business.repositories.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesmanager.core.model.system.optin.Optin;
import com.salesmanager.core.model.system.optin.OptinType;

@Repository
public interface OptinRepository extends JpaRepository<Optin, Long> {

	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = :storeId")
	List<Optin> findByMerchant(@Param("storeId") Integer storeId);
	

	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = :storeId and o.optinType = :optinTyle")
	Optin findByMerchantAndType(@Param("storeId") Integer storeId, @Param("optinTyle") OptinType optinTyle);
	
	@Query("select distinct o from Optin as o  left join fetch o.merchant om where om.id = :storeId and o.code = :code")
	Optin findByMerchantAndCode(@Param("storeId") Integer storeId, @Param("code") String code);

}
