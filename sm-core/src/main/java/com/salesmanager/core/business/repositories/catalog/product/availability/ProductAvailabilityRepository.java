package com.salesmanager.core.business.repositories.catalog.product.availability;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.product.availability.ProductAvailability;
import org.springframework.data.repository.query.Param;

public interface ProductAvailabilityRepository extends JpaRepository<ProductAvailability, Long> {

  
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where p.id=:availabilityId ")
  ProductAvailability getById(@Param("availabilityId") Long availabilityId);
  
  @Query(value = "select distinct p from ProductAvailability p "
      + "left join fetch p.merchantStore pm "
      + "left join fetch p.prices pp "
      + "left join fetch pp.descriptions ppd "
      + "left join fetch p.merchantStore pm "
      + "join fetch p.product ppr "
      + "join fetch ppr.merchantStore pprm "
      + "where p.id=:availabilityId "
      + "and pprm.id=:merchantId")
  ProductAvailability getById(@Param("availabilityId") Long availabilityId, @Param("merchantId") int merchantId);
  

  @Query(value = "select distinct p from ProductAvailability p "
	      + "left join fetch p.merchantStore pm "
	      + "left join fetch p.prices pp "
	      + "left join fetch pp.descriptions ppd "
	      + "join fetch p.product ppr "
	      + "left join fetch ppr.descriptions pprd "
	      + "left join fetch p.productVariant ppi "
	      + "where ppr.sku=:productCode or ppi.sku=:productCode "
	      + "and pm.code=:store")
  List<ProductAvailability> getBySku(@Param("productCode") String productCode, @Param("store") String store);
  
  @Query(value = "select distinct p from ProductAvailability p "
	      + "left join fetch p.merchantStore pm "
	      + "left join fetch p.prices pp "
	      + "left join fetch pp.descriptions ppd "
	      + "join fetch p.product ppr "
	      + "left join fetch ppr.descriptions pprd "
	      + "left join fetch p.productVariant ppi "
	      + "where ppr.sku=:sku or ppi.sku=:sku")
  List<ProductAvailability> getBySku(@Param("sku") String sku);

}
