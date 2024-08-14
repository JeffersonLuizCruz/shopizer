package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductOptionValue;
import org.springframework.data.repository.query.Param;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {

	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = :id")
	ProductOptionValue findOne(@Param("id") Long id);
	
	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = :id  and pm.id = :storeId")
	ProductOptionValue findOne(@Param("storeId") Integer storeId, @Param("id") Long id);
	
	@Query("select distinct p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and pd.language.id = :languageId")
	List<ProductOptionValue> findByStoreId(@Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and p.code = :optionValueCode")
	ProductOptionValue findByCode(@Param("storeId") Integer storeId, @Param("optionValueCode") String optionValueCode);
	
	@Query("select p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and (pd.name like %:name% or p.code like %:name%) and pd.language.id = :languageId")
	List<ProductOptionValue> findByName(@Param("storeId") Integer storeId, @Param("name") String name, @Param("languageId") Integer languageId);

	@Query("select distinct p from ProductOptionValue p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and pd.language.id = :languageId and p.productOptionDisplayOnly = :readOnly")
	List<ProductOptionValue> findByReadOnly(@Param("storeId") Integer storeId, @Param("languageId") Integer languageId, @Param("readOnly") boolean readOnly);

}
