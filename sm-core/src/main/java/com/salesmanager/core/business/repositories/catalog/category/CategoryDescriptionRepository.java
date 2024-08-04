package com.salesmanager.core.business.repositories.catalog.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.category.CategoryDescription;
import org.springframework.data.repository.query.Param;


public interface CategoryDescriptionRepository extends JpaRepository<CategoryDescription, Long> {
	

	@Query("select c from CategoryDescription c where c.category.id = :categoryId")
	List<CategoryDescription> listByCategoryId(@Param("categoryId") Long categoryId);
	



	
}
