package com.salesmanager.core.business.repositories.catalog.product.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.file.DigitalProduct;
import org.springframework.data.repository.query.Param;

public interface DigitalProductRepository extends JpaRepository<DigitalProduct, Long> {

	@Query("select p from DigitalProduct p inner join fetch p.product pp inner join fetch pp.merchantStore ppm where ppm.id =:storeId and pp.id = :productId")
	DigitalProduct findByProduct(@Param("storeId") Integer storeId, @Param("productId") Long productId);
	
	@Query("select p from DigitalProduct p inner join fetch p.product pp inner join fetch pp.merchantStore ppm where p.id = :id")
	DigitalProduct findOne(@Param("id") Long id);
	
	
}
