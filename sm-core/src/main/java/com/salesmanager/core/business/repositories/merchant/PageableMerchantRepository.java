package com.salesmanager.core.business.repositories.merchant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.merchant.MerchantStore;
import org.springframework.data.repository.query.Param;

public interface PageableMerchantRepository extends PagingAndSortingRepository<MerchantStore, Long> {

	/*
	 * List by parent store
	 */
	@Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where mp.code = :code", countQuery = "select count(distinct m) from MerchantStore m join m.parent mp where mp.code = :code")
	Page<MerchantStore> listByStore(@Param("code") String code, Pageable pageable);

	@Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp left join fetch m.country mc left join fetch m.currency mc left join fetch m.zone mz left join fetch m.defaultLanguage md left join fetch m.languages mls where (:storename is null or m.storename like %:storename%)",
			countQuery = "select count(distinct m) from MerchantStore m where (:storename is null or m.storename like %:storename%)")
	Page<MerchantStore> listAll(@Param("storename") String storename, Pageable pageable);

	@Query(value = "select distinct m from MerchantStore m left join fetch m.parent mp "
			+ "left join fetch m.country mc " + "left join fetch m.currency mc left " + "join fetch m.zone mz "
			+ "left join fetch m.defaultLanguage md " + "left join fetch m.languages mls "
			+ "where m.retailer = true and (:storename is null or m.storename like %:storename%)", countQuery = "select count(distinct m) from MerchantStore m join m.parent "
					+ "where m.retailer = true and (:storename is null or m.storename like %:storename%)")
	Page<MerchantStore> listAllRetailers(@Param("storename") String storename, Pageable pageable);

	@Query(value = "select distinct m from MerchantStore m left join m.parent mp " + "left join fetch m.country pc "
			+ "left join fetch m.currency pcu " + "left join fetch m.languages pl " + "left join fetch m.zone pz "
			+ "where mp.code = :storecode or m.code = :storecode "
			+ "and (:storename is null or (m.storename like %:storename% or mp.storename like %:storename%))", countQuery = "select count(distinct m) from MerchantStore m left join m.parent mp "
					+ "where mp.code = :storecode or m.code = :storecode and (:storename is null or (m.storename like %:storename% or mp.storename like %:storename%))")
	Page<MerchantStore> listChilds(@Param("storecode") String storecode, @Param("storename") String storename, Pageable pageable);

	@Query(value = "select * from MERCHANT_STORE m " + "where (m.STORE_CODE = :storecode or (:id is null or m.PARENT_ID = :id)) "
			+ "and (:storename is null or m.STORE_NAME like %:storename%)", countQuery = "select count(*) from {h-schema}MERCHANT_STORE m where (m.STORE_CODE = :storecode or (:id is null or m.PARENT_ID = :id)) and (:storename is null or m.STORE_NAME like %:storename%)", nativeQuery = true)
	Page<MerchantStore> listByGroup(@Param("storecode") String storecode, @Param("id") Integer id, @Param("storename") String storename, Pageable pageable);

}
