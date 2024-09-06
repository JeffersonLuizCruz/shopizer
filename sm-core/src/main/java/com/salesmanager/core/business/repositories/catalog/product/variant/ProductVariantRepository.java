package com.salesmanager.core.business.repositories.catalog.product.variant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.variant.ProductVariant;
import org.springframework.data.repository.query.Param;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
	

	
	
	@Query("select p from ProductVariant p join fetch p.product pr "
			+ "left join fetch p.variation pv "
			+ "left join fetch pv.productOption pvpo "
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			
			+ "left join fetch p.variationValue pvv "
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov "
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions povvpovd "			
			
			+ "left join fetch pv.merchantStore pvm "
			+ "where p.id = :id and pvm.id = :storeId")
	Optional<ProductVariant> findOne(@Param("id") Long id, @Param("storeId") Integer storeId);
	
	@Query("select p from ProductVariant p join fetch p.product pr "
			+ "left join fetch p.variation pv "
			+ "left join fetch pv.productOption pvpo "
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			
			+ "left join fetch p.variationValue pvv "
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov "
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions povvpovd "			
			
			+ "left join fetch pv.merchantStore pvm "
			+ "where p.id in (:ids) and pvm.id = :storeId")
	List<ProductVariant> findByIds(@Param("ids") List<Long> ids, @Param("storeId") Integer storeId);
	
	
	@Query("select p from ProductVariant p join fetch p.product pr "
			+ "left join fetch p.variation pv "
			+ "left join fetch pv.productOption pvpo "
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			
			+ "left join fetch p.variationValue pvv "
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov "
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions povvpovd "			
			
			+ "left join fetch pr.merchantStore prm "
			+ "where p.id = :id and pr.id = :productId and prm.id = :storeId")
	Optional<ProductVariant> findById(@Param("id") Long id, @Param("productId") Long productId, @Param("storeId") Integer storeId);
	
	
	
	@Query("select p from ProductVariant p join fetch p.product pr "
			+ "left join fetch p.variation pv "
			+ "left join fetch pv.productOption pvpo "
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			
			+ "left join fetch p.variationValue pvv "
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov "
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions povvpovd "			
			
			+ "left join fetch pr.merchantStore prm "
			+ "where pvpod.language.id = :languageId "
			+ "and pvpovd.language.id = :languageId "
			+ "and povvpod.language.id = :languageId "
			+ "and povvpovd.language.id = :languageId "
			+ "and pr.id = :productId and p.code = :code and prm.id = :storeId")
	Optional<ProductVariant> findBySku(@Param("code") String code, @Param("productId") Long productId, @Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	
	/**
	 * Gets the whole graph
	 * @param storeId
	 * @param productId
	 * @return
	 */
	@Query(value = "select distinct p from ProductVariant as p " 
			+ "join fetch p.product pr " 
			+ "left join fetch p.variation pv "
			+ "left join fetch pv.productOption pvpo " 
			+ "left join fetch pv.productOptionValue pvpov "
			+ "left join fetch pvpo.descriptions pvpod " 
			+ "left join fetch pvpov.descriptions pvpovd "

			+ "left join fetch p.variationValue pvv " 
			+ "left join fetch pvv.productOption pvvpo "
			+ "left join fetch pvv.productOptionValue pvvpov " 
			+ "left join fetch pvvpo.descriptions povvpod "
			+ "left join fetch pvpov.descriptions pvpovd "
			+ "left join fetch p.productVariantGroup pig "
			+ "left join fetch pig.images pigi "
			+ "left join fetch pigi.descriptions pigid "
			

			+ "left join fetch pv.merchantStore pvm " 
			+ "where pr.id = :productId and pvm.id = :storeId")
	List<ProductVariant> findByProductId(@Param("storeId") Integer storeId, @Param("productId") Long productId);

	
	
	@Query("select p from ProductVariant p join fetch p.product pr where p.sku = ?1 and pr.id = ?2")
	ProductVariant existsBySkuAndProduct(@Param("sku") String sku, @Param("productId") Long productId);
	

	

}
