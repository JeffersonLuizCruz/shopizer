package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductAttribute;
import org.springframework.data.repository.query.Param;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

	@Query("select p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore where p.id = :id")
	ProductAttribute findOne(@Param("id") Long id);
	
	@Query("select p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = :storeId and po.id = :id")
	List<ProductAttribute> findByOptionId(@Param("storeId") Integer storeId, @Param("id") Long id);
	
	@Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = :storeId and po.id = :id")
	List<ProductAttribute> findByOptionValueId(@Param("storeId") Integer storeId, @Param("id") Long id);
	
	@Query("select distinct p from ProductAttribute p "
			+ "join fetch p.product pr "
			+ "left join fetch p.productOption po "
			+ "left join fetch p.productOptionValue pov "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch pov.descriptions povd "
			+ "left join fetch pov.merchantStore povm "
			+ "where povm.id = :storeId and pr.id = :productId and p.id in :ids")
	List<ProductAttribute> findByAttributeIds(@Param("storeId") Integer storeId, @Param("productId") Long productId, @Param("ids") List<Long> ids);

	@Query("select distinct p from ProductAttribute p join fetch p.product pr left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = ?1")
	List<ProductAttribute> findByProductId(Integer storeId, Long productId);
	
	@Query(value="select distinct p from ProductAttribute p join fetch p.product pr left join fetch pr.categories prc left join fetch p.productOption po left join fetch p.productOptionValue pov left join fetch po.descriptions pod left join fetch pov.descriptions povd left join fetch po.merchantStore pom where pom.id = :storeId and prc.id IN (select c.id from Category c where c.lineage like :lineage% and povd.language.id = :languageId)")
	List<ProductAttribute> findOptionsByCategoryLineage(@Param("storeId") Integer storeId, @Param("lineage") String lineage, @Param("languageId") Integer languageId);
}
