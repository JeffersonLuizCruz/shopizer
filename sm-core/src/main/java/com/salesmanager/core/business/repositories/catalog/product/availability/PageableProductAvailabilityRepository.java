package com.salesmanager.core.business.repositories.catalog.product.availability;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import org.springframework.data.repository.query.Param;

public interface PageableProductAvailabilityRepository extends PagingAndSortingRepository<ProductAvailability, Long> {

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " + "left join fetch pp.descriptions ppd " + "join fetch p.product ppr "
			+ "left join fetch ppr.merchantStore pprm " + "where ppr.id=:productId " + "and pprm.id=:storeId "
			+ "and (:child is null or pm.code like %:child%)", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "join p.prices pp " + "join pp.descriptions ppd "
					+ "join p.merchantStore pm " + "join p.product ppr " + "join ppr.merchantStore pprm "
					+ "where ppr.id=:productId " + "and pprm.id=:storeId " + "and (:child is null or pm.code like %:child%)")
	Page<ProductAvailability> listByStore(@Param("productId") Long productId, @Param("storeId") Integer storeId, @Param("child") String child, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " + "left join fetch pp.descriptions ppd " + "join fetch p.product ppr "
			+ "left join fetch ppr.merchantStore pprm "
			+ "where pm.id=?1 ", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "where pm.id=:storeId ")
	Page<ProductAvailability> listByStore(@Param("storeId") Integer storeId, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " 
			+ "left join fetch pp.descriptions ppd " 
			+ "join fetch p.product ppr "
			+ "join fetch ppr.merchantStore pprm "
			+ "left join fetch ppr.variants ppri "
			+ "left join fetch ppri.availabilities ppria "
			+ "left join fetch ppria.prices ppriap "
			+ "left join fetch ppriap.descriptions ppriapd "
			+ "where ppr.id=:productId and pm.code=:store", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "join p.product ppr left join ppr.variants ppri left join ppri.availabilities ppria " + "where ppr.id=:productId "
							+ "and pm.code=:store")
	Page<ProductAvailability> getByProductId(@Param("productId") Long productId, @Param("store") String store, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " + "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " 
			+ "left join fetch pp.descriptions ppd " 
			+ "join fetch p.product ppr "
			+ "left join fetch p.productVariant ppi " 
			+ "where ppr.sku=:productCode or ppi.sku=:productCode "
			+ "and pm.code=:store", countQuery = "select  count(p) from ProductAvailability p " + "join p.merchantStore pm "
					+ "join p.product ppr " + "left join p.productVariant ppi " + "where ppr.sku=:productCode or ppi.sku=:productCode "
					+ "and pm.code=:store")
	Page<ProductAvailability> getBySku(@Param("productCode") String productCode, @Param("store") String store, Pageable pageable);

	@Query(value = "select distinct p from ProductAvailability p " 
			+ "left join fetch p.merchantStore pm "
			+ "left join fetch p.prices pp " 
			+ "left join fetch pp.descriptions ppd " + "join fetch p.product ppr "
			+ "left join fetch p.productVariant ppi "
			+ "where ppr.sku=:productCode or ppi.sku=:productCode", countQuery = "select  count(p) from ProductAvailability p "
					+ "join p.merchantStore pm " + "join p.product ppr " + "left join p.productVariant ppi "
					+ "where ppr.sku=:productCode or ppi.sku=:productCode ")
	Page<ProductAvailability> getBySku(@Param("productCode") String productCode, Pageable pageable);

		
	
}
