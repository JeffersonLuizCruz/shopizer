package com.salesmanager.core.business.repositories.catalog.product.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.type.ProductType;
import org.springframework.data.repository.query.Param;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

	@Query(value = "select p from ProductType p join fetch p.merchantStore pm where p.code=:code")
	ProductType findByCode(@Param("code") String code);

	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.code=:code and (pm is null or pm.id=:store)")
	ProductType findByCode(@Param("code") String code, @Param("store") Integer store);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.id=:id and (pm is null or pm.id=:store)")
	ProductType findById(@Param("id") Long id, @Param("store") Integer store, int language);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd left join fetch p.merchantStore pm where p.id=:id and (pm is null or pm.id=:store)")
	ProductType findById(@Param("id") Long id, @Param("store") Integer store);
	
	@Query(value = "select p from ProductType p left join fetch p.descriptions pd join fetch p.merchantStore pm where p.id in :id and (pm is null or pm.id=:store)")
	List<ProductType> findByIds(@Param("id") List<Long> id, @Param("store") Integer store, int language);

}
