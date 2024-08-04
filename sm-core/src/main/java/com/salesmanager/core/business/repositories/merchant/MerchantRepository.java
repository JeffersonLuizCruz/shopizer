package com.salesmanager.core.business.repositories.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.merchant.MerchantStore;
import org.springframework.data.repository.query.Param;

public interface MerchantRepository extends JpaRepository<MerchantStore, Integer>, MerchantRepositoryCustom {


	@Query("select m from MerchantStore m "
			+ "left join fetch m.parent mp"
			+ "left join fetch m.country mc "
			+ "left join fetch m.currency mc "
			+ "left join fetch m.zone mz "
			+ "left join fetch m.defaultLanguage md "
			+ "left join fetch m.languages mls where m.code = :code")
	MerchantStore findByCode(@Param("code") String code);
	
	@Query("select m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where m.id = :id")
	MerchantStore getById(@Param("id") int id);
	

	@Query("select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where mp.code = :code")
	List<MerchantStore> getByParent(@Param("code") String code);

	@Query("SELECT COUNT(m) > 0 FROM MerchantStore m WHERE m.code = :code")
	boolean existsByCode(String code);
	
	@Query("select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename) from MerchantStore m")
	List<MerchantStore> findAllStoreNames();
	
	 @Query(value = "select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename) from MerchantStore m left join m.parent mp "
	  			+ "where mp.code = :storecode or m.code = :storecode")
	List<MerchantStore> findAllStoreNames(@Param("storecode") String storecode);
	 
	 @Query(value = "select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename) from MerchantStore m left join m.parent mp "
	  			+ "where mp.code in :storeCode or m.code in :storeCode")
	List<MerchantStore> findAllStoreNames(@Param("storeCode") List<String> storeCode);

	@Query("select new com.salesmanager.core.model.merchant.MerchantStore(m.id, m.code, m.storename, m.storeEmailAddress) from MerchantStore m")
	List<MerchantStore> findAllStoreCodeNameEmail();
	
	@Query(
	  value = "select * from {h-schema}MERCHANT_STORE m "
	  		+ "where m.STORE_CODE = :storeCode or :id is null or m.PARENT_ID = :id",
	  nativeQuery = true)
	  List<MerchantStore> listByGroup(@Param("storeCode") String storeCode, @Param("id") Integer id);
}
