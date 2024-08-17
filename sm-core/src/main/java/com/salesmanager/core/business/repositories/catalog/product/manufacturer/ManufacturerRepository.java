package com.salesmanager.core.business.repositories.catalog.product.manufacturer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer;
import org.springframework.data.repository.query.Param;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

	@Query("select count(distinct p) from Product as p where p.manufacturer.id=:manufacturerId")
	Long countByProduct(@Param("manufacturerId") Long manufacturerId);
	
	@Query("select m from Manufacturer m left join fetch m.descriptions md join fetch m.merchantStore ms where ms.id=:storeId and md.language.id=:languageId")
	List<Manufacturer> findByStoreAndLanguage(@Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select m from Manufacturer m left join fetch  m.descriptions md join fetch m.merchantStore ms where m.id=:id")
	Manufacturer findOne(@Param("id") Long id);
	
	@Query("select m from Manufacturer m left join fetch m.descriptions md join fetch m.merchantStore ms where ms.id=:storeId")
	List<Manufacturer> findByStore(@Param("storeId") Integer storeId);
	
    @Query("select m from Manufacturer m join fetch m.descriptions md join fetch m.merchantStore ms join fetch md.language mdl where ms.id=:storeId and mdl.id=:languageId and (:name is null or md.name like %:name%)")
	//@Query("select m from Manufacturer m join fetch m.descriptions md join fetch m.merchantStore ms join fetch md.language mdl where ms.id=?1 and mdl.id=?2")
	//@Query("select m from Manufacturer m left join m.descriptions md join fetch m.merchantStore ms where ms.id=?1")
	List<Manufacturer> findByStore(@Param("storeId") Integer storeId, @Param("languageId") Integer languageId, @Param("name") String name);
	

	@Query("select distinct manufacturer from Product as p join p.manufacturer manufacturer join manufacturer.descriptions md join p.categories categs where categs.id in (:categoryIds) and md.language.id=:languageId")
	List<Manufacturer> findByCategoriesAndLanguage(@Param("categoryIds") List<Long> categoryIds, @Param("languageId") Integer languageId);
	
	@Query("select m from Manufacturer m left join m.descriptions md join fetch m.merchantStore ms where m.code=:code and ms.id=:storeId")
	Manufacturer findByCodeAndMerchandStore(@Param("code") String code, @Param("storeId") Integer storeId);
	
	@Query("select count(distinct m) from Manufacturer as m where m.merchantStore.id=:storeId")
	int count(@Param("storeId") Integer storeId);
	
	@Query(value="select distinct manufacturer from Product as p "
			+ "join p.manufacturer manufacturer "
			+ "left join manufacturer.descriptions pmd "
			+ "join fetch manufacturer.merchantStore pms "
			+ "join p.categories pc "
			+ "where pms.id = :storeId "
			+ "and pc.id IN (select c.id from Category c where c.lineage like %:lineage% and pmd.language.id = :languageId)")
	List<Manufacturer> findByProductInCategoryId(@Param("storeId") Integer storeId, @Param("lineage") String lineage, @Param("languageId") Integer languageId);
}
