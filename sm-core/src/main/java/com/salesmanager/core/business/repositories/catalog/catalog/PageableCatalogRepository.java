package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import org.springframework.data.repository.query.Param;

public interface PageableCatalogRepository extends PagingAndSortingRepository<Catalog, Long> {

	
	  @Query(value = "select distinct c from Catalog c join fetch c.merchantStore cm left join fetch c.entry ce where cm.id=:storeId and (:code is null or c.code like %:code%)",
		      countQuery = "select count(c) from Catalog c join c.merchantStore cm join c.entry ce where cm.id=:storeId and (:code is null or c.code like %:code%)")
		  Page<Catalog> listByStore(@Param("storeId") Integer storeId, @Param("code") String code, Pageable pageable);

	
}
