package com.salesmanager.core.business.repositories.catalog.product.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.review.ProductReview;
import org.springframework.data.repository.query.Param;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {


	@Query("select p from ProductReview p join fetch p.customer pc join fetch p.product pp join fetch pp.merchantStore ppm left join fetch p.descriptions pd where p.id = :id")
	ProductReview findOne(@Param("id") Long id);
	
	@Query("select p from ProductReview p join fetch p.customer pc join fetch p.product pp join fetch pp.merchantStore ppm left join fetch p.descriptions pd where pc.id = :customerId")
	List<ProductReview> findByCustomer(@Param("customerId") Long customerId);
	
	@Query("select p from ProductReview p left join fetch p.descriptions pd join fetch p.customer pc join fetch pc.merchantStore pcm left join fetch pc.defaultLanguage pcl left join fetch pc.attributes pca left join fetch pca.customerOption pcao left join fetch pca.customerOptionValue pcav left join fetch pcao.descriptions pcaod left join fetch pcav.descriptions pcavd join fetch p.product pp join fetch pp.merchantStore ppm  join fetch p.product pp join fetch pp.merchantStore ppm left join fetch p.descriptions pd where pp.id = :productId")
	List<ProductReview> findByProduct(@Param("productId") Long productId);
	
	@Query("select p from ProductReview p join fetch p.product pp join fetch pp.merchantStore ppm  where pp.id = :productId")
	List<ProductReview> findByProductNoCustomers(@Param("productId") Long productId);
	
	@Query("select p from ProductReview p left join fetch p.descriptions pd join fetch p.customer pc join fetch pc.merchantStore pcm left join fetch pc.defaultLanguage pcl left join fetch pc.attributes pca left join fetch pca.customerOption pcao left join fetch pca.customerOptionValue pcav left join fetch pcao.descriptions pcaod left join fetch pcav.descriptions pcavd join fetch p.product pp join fetch pp.merchantStore ppm  join fetch p.product pp join fetch pp.merchantStore ppm left join fetch p.descriptions pd where pp.id = :productId and pd.language.id = :languageId")
	List<ProductReview> findByProduct(@Param("productId") Long productId, @Param("languageId") Integer languageId);
	
	@Query("select p from ProductReview p left join fetch p.descriptions pd join fetch p.customer pc join fetch pc.merchantStore pcm left join fetch pc.defaultLanguage pcl left join fetch pc.attributes pca left join fetch pca.customerOption pcao left join fetch pca.customerOptionValue pcav left join fetch pcao.descriptions pcaod left join fetch pcav.descriptions pcavd join fetch p.product pp join fetch pp.merchantStore ppm  join fetch p.product pp join fetch pp.merchantStore ppm left join fetch p.descriptions pd where pp.id = :productId and pc.id = :customerId")
	ProductReview findByProductAndCustomer(@Param("productId") Long productId, @Param("customerId") Long customerId);
	
	
}
