package com.salesmanager.core.business.repositories.content;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentType;
import org.springframework.data.repository.query.Param;

public interface ContentRepository extends JpaRepository<Content, Long>,  ContentRepositoryCustom  {

	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = :contentType and cm.id = :storeId and cd.language.id = :languageId order by c.sortOrder asc")
	List<Content> findByType(@Param("contentType") ContentType contentType, @Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = :contentType and cm.id = :storeId order by c.sortOrder asc")
	List<Content> findByType(@Param("contentType") ContentType contentType, @Param("storeId") Integer storeId);
	
	@Query("select c from Content c join fetch c.merchantStore cm where c.code = :code and c.contentType = :contentType and cm.id = :storeId")
	Content findByCodeAndType(@Param("code") String code, @Param("contentType") ContentType contentType, @Param("storeId") Integer storeId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType in (:contentTypes) and cm.id = :storeId and cd.language.id = :languageId order by c.sortOrder asc")
	List<Content> findByTypes(@Param("contentTypes") List<ContentType> contentTypes, @Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType in (:contentTypes) and cm.id = :storeId order by c.sortOrder asc")
	List<Content> findByTypes(@Param("contentTypes") List<ContentType> contentTypes, @Param("storeId") Integer storeId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.code = :code and cm.id = :storeId")
	Content findByCode(@Param("code") String code, @Param("storeId") Integer storeId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.contentType = :contentType and cm.id=:storeId and c.code like :code and cd.language.id = :languageId")
	List<Content> findByCodeLike(@Param("contentType") ContentType contentType, @Param("code") String code, @Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.code = :code and cm.id = :storeId and cd.language.id = :languageId")
	Content findByCode(@Param("code") String code, @Param("storeId") Integer storeId, @Param("languageId") Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.id = :contentId and cd.language.id = :languageId")
	Content findByIdAndLanguage(@Param("contentId") Long contentId, @Param("languageId") Integer languageId);
	
	@Query("select c from Content c left join fetch c.descriptions cd join fetch c.merchantStore cm where c.id = :contentId")
	Content findOne(@Param("contentId") Long contentId);


}
