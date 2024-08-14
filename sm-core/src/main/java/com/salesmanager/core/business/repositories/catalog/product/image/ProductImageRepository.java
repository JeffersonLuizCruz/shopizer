package com.salesmanager.core.business.repositories.catalog.product.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.image.ProductImage;
import org.springframework.data.repository.query.Param;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm where p.id = :id")
	ProductImage findOne(@Param("id") Long id);
	
	@Query("select p from ProductImage p left join fetch p.descriptions pd inner join fetch p.product pp inner join fetch pp.merchantStore ppm where pp.id = :productId and ppm.code = :storeCode and p.id = :imageId")
	ProductImage finById(@Param("imageId") Long imageId, @Param("productId") Long productId, @Param("storeCode") String storeCode);
	
	
}
