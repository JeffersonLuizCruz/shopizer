package com.salesmanager.core.business.repositories.catalog.product.attribute;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.attribute.ProductOption;
import org.springframework.data.repository.query.Param;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = :id")
	ProductOption findOne(@Param("id") Long id);
	
	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where p.id = :id and pm.id = :storeId")
	ProductOption findOne(@Param("storeId") Integer storeId, @Param("id") Long id);
	
	@Query("select distinct p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and pd.language.id = :languageId")
	List<ProductOption> findByStoreId(@Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and pd.name like %:name% and pd.language.id = :languageId")
	List<ProductOption> findByName(@Param("storeId") Integer storeId, @Param("name") String name, @Param("languageId") Integer languageId);
	
	@Query("select p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and p.code = :optionCode")
	ProductOption findByCode(@Param("storeId") Integer storeId, @Param("optionCode") String optionCode);
	
	@Query("select distinct p from ProductOption p join fetch p.merchantStore pm left join fetch p.descriptions pd where pm.id = :storeId and p.code = :languageId and p.readOnly = :readOnly")
	List<ProductOption> findByReadOnly(@Param("storeId") Integer storeId, @Param("languageId") Integer languageId, @Param("readOnly") boolean readOnly);
	

}
