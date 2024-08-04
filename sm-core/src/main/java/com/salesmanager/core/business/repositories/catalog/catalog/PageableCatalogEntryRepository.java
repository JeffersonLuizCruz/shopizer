package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.catalog.catalog.CatalogCategoryEntry;
import org.springframework.data.repository.query.Param;

public interface PageableCatalogEntryRepository extends PagingAndSortingRepository<CatalogCategoryEntry, Long> {

	
/*	  @Query(value = "select distinct c from CatalogEntry c join fetch c.product cp "
	  		+ "join fetch c.category cc "
	  		+ "join fetch c.catalog cl "
	  		+ "join fetch cl.merchantStore clm "
	  		+ "left join fetch cp.descriptions cpd "
	  		+ "left join fetch cc.descriptions ccd "
	  		+ "where cl.id=?1 and "
	  		+ "clm.id=?2 and "
	  		+ "cpd.language.id=?3 and (?4 is null or cpd.name like %?4%)",
		      countQuery = "select  count(c) from CatalogEntry c join c.product cp join c.category cc join c.catalog cl join cl.merchantStore clm join cp.descriptions cpd where cl.id=?1 and clm.id=?2 and cpd.language.id=?3 and (?4 is null or cpd.name like %?4%)")*/
	  @Query(value = "select distinct c from CatalogCategoryEntry c  "
		  		+ "join fetch c.category cc "
		  		+ "join fetch c.catalog cl "
		  		+ "join fetch cl.merchantStore clm "
		  		+ "left join fetch cc.descriptions ccd "
		  		+ "where cl.id=:catalogId and "
		  		+ "clm.id=:storeId and "
		  		+ "ccd.language.id=:languageId",
			      countQuery = "select  count(c) from CatalogCategoryEntry c join c.category cc join c.catalog cl join cl.merchantStore clm join cc.descriptions ccd where cl.id=:catalogId and clm.id=:storeId and ccd.language.id=:languageId")
		  Page<CatalogCategoryEntry> listByCatalog(@Param("catalogId") Long catalogId, @Param("storeId") Integer storeId, @Param("languageId") Integer languageId, Pageable pageable);

	
}
