package com.salesmanager.core.business.repositories.content;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.merchant.MerchantStore;
import org.springframework.data.repository.query.Param;

public interface PageContentRepository extends PagingAndSortingRepository<MerchantStore, Long> {
	
	

	@Query(value = "select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = :contentType and cm.id = :storeId order by c.sortOrder asc",
      countQuery = "select count(distinct c) from Content c join c.merchantStore cm where c.contentType = :contentType and cm.id = :storeId")
	Page<Content> findByContentType(@Param("contentType") ContentType contentType, @Param("storeId") Integer storeId, Pageable pageable);
	
	@Query(value = "select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm join fetch cd.language cdl where c.contentType = :contentTypes and cm.id = :storeId and cdl.id = :language order by c.sortOrder asc",
		      countQuery = "select count(distinct c) from Content c join c.merchantStore cm where c.contentType = :contentTypes and cm.id = :storeId")
			Page<Content> findByContentType(@Param("contentTypes") ContentType contentTypes, @Param("storeId") Integer storeId, @Param("language") Integer language, Pageable pageable);
	
	

}
