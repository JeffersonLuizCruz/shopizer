package com.salesmanager.core.business.repositories.catalog.product.variant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.variant.ProductVariantGroup;
import org.springframework.data.repository.query.Param;

public interface ProductVariantGroupRepository extends JpaRepository<ProductVariantGroup, Long> {

	
	@Query("select distinct p from ProductVariantGroup p"
			+ " left join fetch p.productVariants pp"
			+ " left join fetch p.images ppi"
			+ " left join fetch ppi.descriptions ppid "
			+ " where p.id = :id and p.merchantStore.code = :storeCode")
	Optional<ProductVariantGroup> findOne(@Param("id") Long id, @Param("storeCode") String storeCode);
	
	
	@Query("select distinct p from ProductVariantGroup p "
			+ "left join fetch p.productVariants pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where pp.id = :productVariantId and p.merchantStore.code = :storeCode")
	Optional<ProductVariantGroup> finByProductVariant(@Param("productVariantId") Long productVariantId, @Param("storeCode") String storeCode);
	
	@Query("select distinct p from ProductVariantGroup p "
			+ "left join fetch p.productVariants pp "
			+ "left join fetch p.images ppi "
			+ "left join fetch ppi.descriptions ppid "
			+ "join fetch pp.product ppp "
			+ "join fetch ppp.merchantStore pppm "
			+ "where ppp.id = :productId and p.merchantStore.code = :storeCode")
	List<ProductVariantGroup> finByProduct(@Param("productId") Long productId, @Param("storeCode") String storeCode);
	

}
