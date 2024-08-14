package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductOptionSet;
import org.springframework.data.repository.query.Param;

public interface ProductOptionSetRepository extends JpaRepository<ProductOptionSet, Long> {

	@Query("select distinct p from ProductOptionSet p join fetch p.store pm left join fetch p.option po left join fetch po.descriptions pod left join fetch p.values pv left join fetch pv.descriptions pvd where pm.id = :storeId and p.id = :id and pod.language.id = :language")
	ProductOptionSet findOne(@Param("storeId") Integer storeId, @Param("id") Long id, @Param("language") Integer language);
	
	@Query("select distinct p from ProductOptionSet p join fetch p.store pm left join fetch p.option po left join fetch po.descriptions pod left join fetch p.values pv left join fetch pv.descriptions pvd where pm.id = ?1 and pod.language.id = ?2")
	List<ProductOptionSet> findByStore(@Param("storeId") Integer storeId, @Param("language") Integer language);
	
	@Query("select distinct p from ProductOptionSet p "
			+ "join fetch p.store pm left join fetch p.productTypes pt "
			+ "left join fetch p.option po "
			+ "left join fetch po.descriptions pod "
			+ "left join fetch p.values pv "
			+ "left join fetch pv.descriptions pvd where pt.id= :typeId and pm.id = :storeId and pod.language.id = :language")
	List<ProductOptionSet> findByProductType(@Param("typeId") Long typeId, @Param("storeId") Integer storeId, @Param("language") Integer language);
	
	@Query("select p from ProductOptionSet p join fetch p.store pm left join fetch p.option po left join fetch po.descriptions pod left join fetch p.values pv left join fetch pv.descriptions pvd where pm.id = :storeId and p.code = :code")
	ProductOptionSet findByCode(@Param("storeId") Integer storeId, @Param("code") String code);

}
