package com.salesmanager.core.business.repositories.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.user.Permission;
import org.springframework.data.repository.query.Param;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, PermissionRepositoryCustom {

	
	@Query("select p from Permission as p where p.id = :id")
	Permission findOne(@Param("id") Integer id);
	
	@Query("select p from Permission as p order by p.id")
	List<Permission> findAll();
	
	@Query("select distinct p from Permission as p join fetch p.groups groups where groups.id in (:groupIds)")
	List<Permission> findByGroups(@Param("groupIds") Set<Integer> groupIds);
}
