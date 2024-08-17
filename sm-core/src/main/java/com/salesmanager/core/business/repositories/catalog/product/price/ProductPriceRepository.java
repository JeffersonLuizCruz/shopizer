package com.salesmanager.core.business.repositories.catalog.product.price;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.price.ProductPrice;
import org.springframework.data.repository.query.Param;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

	@Query("select p from ProductPrice p " + "left join fetch p.descriptions pd "
			+ "inner join fetch p.productAvailability pa " + "inner join fetch pa.product pap "
			+ "inner join fetch pap.merchantStore papm " + "where p.id = :id")
	ProductPrice findOne(@Param("id") Long id);

	// SELECT distinct pp.PRODUCT_PRICE_AMOUNT, p.SKU
	// FROM SALESMANAGER.PRODUCT_PRICE AS pp
	// INNER JOIN SALESMANAGER.PRODUCT_AVAILABILITY AS pa ON pa.PRODUCT_AVAIL_ID =
	// pp.PRODUCT_AVAIL_ID
	// INNER JOIN SALESMANAGER.PRODUCT AS p ON p.PRODUCT_ID = pa.PRODUCT_ID
	// INNER JOIN SALESMANAGER.PRODUCT_CATEGORY AS pc on p.PRODUCT_ID =
	// pc.PRODUCT_ID
	// WHERE pc.CATEGORY_ID = 1
	// ORDER BY pp.PRODUCT_PRICE_AMOUNT;

	// @Query("select p from ProductPrice p join fetch p.productAvailability pd
	// inner join fetch p.productAvailability pa inner join fetch pa.product pap
	// inner join fetch pap.merchantStore papm where p.id = ?1")
	// List<ProductPrice> priceListByCategory(Long id, Integer storeId);

	@Query(value = "select distinct p from ProductPrice p " + "left join fetch p.productAvailability pa "
			+ "left join fetch pa.merchantStore pm " + "left join fetch p.descriptions pd "
			+ "join fetch pa.product pap " + "left join fetch pa.productVariant ppi "
			+ "where pap.sku=:sku or ppi.sku=:sku and pm.code=:store")
	List<ProductPrice> findByProduct(@Param("sku") String sku, @Param("") String store);

	@Query(value = "select distinct p from ProductPrice p " + "left join fetch p.productAvailability pa "
			+ "left join fetch pa.merchantStore pm " + "left join fetch p.descriptions pd "
			+ "join fetch pa.product pap " + "left join fetch pa.productVariant ppi "
			+ "where pap.sku=:sku or ppi.sku=:sku and p.id=:priceId and pm.code=:store")
	ProductPrice findByProduct(@Param("sku") String sku, @Param("priceId") Long priceId, @Param("store") String store);

	@Query(value = "select distinct p from ProductPrice p " + "left join fetch p.productAvailability pa "
			+ "left join fetch pa.merchantStore pm " + "left join fetch p.descriptions pd "
			+ "join fetch pa.product pap " + "left join fetch pa.productVariant ppi "
			+ "where pap.sku=:sku or ppi.sku=:sku and pa.id=:ProductInventory and pm.code=:store")
	List<ProductPrice> findByProductInventoty(@Param("sku") String sku, @Param("ProductInventory") Long ProductInventory, @Param("store") String store);

}
