package com.salesmanager.core.business.repositories.catalog.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.category.Category;
import org.springframework.data.repository.query.Param;


public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
	

	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cd.seUrl like :friendlyUrl and cm.id = :storeId order by c.sortOrder asc")
	List<Category> listByFriendlyUrl(@Param("storeId") Integer storeId, @Param("friendlyUrl") String friendlyUrl);
	
	@Query("select c from Category c left join fetch c.descriptions cd "
			+ "join fetch cd.language cdl join fetch c.merchantStore cm "
			+ "where cd.seUrl=:friendlyUrl and cdl.id=:languageId and cm.id = :storeId")
	Category findByFriendlyUrl(@Param("storeId") Integer storeId, @Param("friendlyUrl") String friendlyUrl, @Param("languageId") Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cd.name like %:name% and cdl.id=:languageId and cm.id = :storeId order by c.sortOrder asc")
	List<Category> findByName(@Param("storeId") Integer storeId, @Param("name") String name, @Param("languageId") Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.code=:code and cm.id = :storeId")
	Category findByCode(@Param("storeId") Integer storeId, @Param("code") String code);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.code in (:codes) and cdl.id=:languageId and cm.id = :storeId order by c.sortOrder asc")
	List<Category> findByCodes(@Param("storeId") Integer storeId, @Param("codes") List<String> codes, @Param("languageId") Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.id in (:ids) and cdl.id=:languageId and cm.id = :storeId order by c.sortOrder asc")
	List<Category> findByIds(@Param("storeId") Integer storeId, @Param("ids") List<Long> ids, @Param("languageId") Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.categories where cm.id=:storeId and c.id = :categoryId and cdl.id=:languageId")
	Category findById(@Param("storeId") Integer storeId, @Param("categoryId") Long categoryId, @Param("languageId") Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.categories where c.id = ?1 and cdl.id=?2")
	Category findByIdAndLanguage(Long categoryId, Integer languageId);
	
	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.categories where c.id = :categoryId and cm.id=:storeId")
	Category findByIdAndStore(@Param("categoryId") Long categoryId, @Param("storeId") Integer storeId);
	
	@Query("select c from Category c left join fetch c.parent cp left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.categories where cm.code=:merchant and c.id = :categoryId")
	Category findById(@Param("categoryId") Long categoryId, @Param("merchant") String merchant);
	
	@Query("select c from Category c left join fetch c.parent cp left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.categories where c.id = :categoryId")
	Optional<Category> findById(@Param("categoryId") Long categoryId);

	@Query("select c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.code=:merchantStoreCode and c.code=:code")
	Category findByCode(@Param("merchantStoreCode") String merchantStoreCode, @Param("code") String code);
	
	@Query("select c from Category c join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where c.id=:categoryId")
	Category findOne(@Param("categoryId") Long categoryId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId and c.lineage like %:linenage% order by c.lineage, c.sortOrder asc")
	List<Category> findByLineage(@Param("merchantId") Integer merchantId, @Param("linenage") String linenage);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.code= :storeCode and c.lineage like %:linenage% order by c.lineage, c.sortOrder asc")
	List<Category> findByLineage(@Param("storeCode") String storeCode, @Param("linenage") String linenage);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId and c.depth >= :depth order by c.lineage, c.sortOrder asc")
	List<Category> findByDepth(@Param("merchantId") Integer merchantId, @Param("depth") int depth);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId and cdl.id=:languageId and c.depth >= :depth order by c.lineage, c.sortOrder asc")
	List<Category> findByDepth( @Param("merchantId") Integer merchantId, @Param("depth") int depth, @Param("languageId") Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId and cdl.id=:languageId and c.depth >= :depth and (:name is null or cd.name like %:name%) order by c.lineage, c.sortOrder asc")
	List<Category> find(@Param("merchantId") Integer merchantId, @Param("depth") int depth, @Param("languageId") Integer languageId, @Param("name") String name);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId and cdl.id=:languageId and c.depth >= :depth and c.featured=true order by c.lineage, c.sortOrder asc")
	List<Category> findByDepthFilterByFeatured(@Param("merchantId") Integer merchantId, @Param("depth") int depth, @Param("languageId") Integer languageId);

	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm left join fetch c.parent cp where cp.id=:parentId and cdl.id=:languageId order by c.lineage, c.sortOrder asc")
	List<Category> findByParent(@Param("parentId") Long parentId, @Param("languageId") Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId and cdl.id=:languageId order by c.lineage, c.sortOrder asc")
	List<Category> findByStore(@Param("merchantId") Integer merchantId, @Param("languageId") Integer languageId);
	
	@Query("select distinct c from Category c left join fetch c.descriptions cd join fetch cd.language cdl join fetch c.merchantStore cm where cm.id=:merchantId order by c.lineage, c.sortOrder asc")
	List<Category> findByStore(@Param("merchantId") Integer merchantId);
	
	@Query("select count(distinct c) from Category as c where c.merchantStore.id=:storeId")
	int count(@Param("storeId") Integer storeId);


	
}
