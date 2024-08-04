package com.salesmanager.core.business.repositories.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.system.MerchantConfiguration;
import com.salesmanager.core.model.system.MerchantConfigurationType;
import org.springframework.data.repository.query.Param;

public interface MerchantConfigurationRepository extends JpaRepository<MerchantConfiguration, Long> {

	//List<MerchantConfiguration> findByModule(String moduleName);
	
	//MerchantConfiguration findByCode(String code);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=:id")
	List<MerchantConfiguration> findByMerchantStore(@Param("id") Integer id);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=:id and m.key=:key")
	MerchantConfiguration findByMerchantStoreAndKey(@Param("id") Integer id, @Param("key") String key);
	
	@Query("select m from MerchantConfiguration m join fetch m.merchantStore ms where ms.id=:id and m.merchantConfigurationType=:type")
	List<MerchantConfiguration> findByMerchantStoreAndType(@Param("id") Integer id, @Param("type") MerchantConfigurationType type);
}
