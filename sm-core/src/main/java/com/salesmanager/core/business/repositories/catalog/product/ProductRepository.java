package com.salesmanager.core.business.repositories.catalog.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.Product;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {


	@Query(value="SELECT " +
			"CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
			"FROM " +
			"Product p " +
			"JOIN MerchantStore m ON m.id = :store " +
			"LEFT JOIN ProductVariant pv ON pv.product.id = p.id " +
			"WHERE (pv.sku = :sku OR p.sku = :sku)")
	boolean existsBySku(@Param("sku") String sku, @Param("store") Integer store);
	
	@Query(

			value = "select p.PRODUCT_ID from {h-schema}PRODUCT p join {h-schema}MERCHANT_STORE m ON p.MERCHANT_ID = m.MERCHANT_ID left join {h-schema}PRODUCT_VARIANT i ON i.PRODUCT_ID = p.PRODUCT_ID where p.SKU=:sku or i.SKU=:sku and m.MERCHANT_ID=:consultId",
			nativeQuery = true
	)
	List<Object> findBySku(@Param("sku") String sku, @Param("consultId") Integer consultId);

}
