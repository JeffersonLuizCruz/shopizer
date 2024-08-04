package com.salesmanager.core.business.repositories.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.user.Group;
import com.salesmanager.core.model.user.GroupType;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Integer> {


	Group findById(Long id);
	
	@Query("select distinct g from Group as g left join fetch g.permissions perms order by g.id")
	List<Group> findAll();
	
	@Query("select distinct g from Group as g left join fetch g.permissions perms where perms.id in (:permissionIds) ")
	List<Group> findByPermissions(@Param("permissionIds") Set<Integer> permissionIds);
	
	@Query("select distinct g from Group as g left join fetch g.permissions perms where g.id in (:groupIds) ")
	List<Group> findByIds(@Param("groupIds") Set<Integer> groupIds);
	
	@Query("select distinct g from Group as g left join fetch g.permissions perms where g.groupName in (:groupeNames) ")
	List<Group> findByNames(@Param("groupeNames") List<String> groupeNames);
	
	@Query("select distinct g from Group as g left join fetch g.permissions perms where g.groupType = :type")
	List<Group> findByType(@Param("type") GroupType type);
	
	@Query("select g from Group as g left join fetch g.permissions perms where g.groupName =:name")
	Group findByGroupName(@Param("name") String name);
}
