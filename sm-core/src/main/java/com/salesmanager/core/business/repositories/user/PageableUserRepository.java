package com.salesmanager.core.business.repositories.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.salesmanager.core.model.user.User;
import org.springframework.data.repository.query.Param;

public interface PageableUserRepository extends PagingAndSortingRepository<User, Long> {
	
	  @Query(value = "select distinct u from User as u left join fetch u.groups ug left join fetch ug.permissions ugp left join fetch u.defaultLanguage ud join fetch u.merchantStore um where um.code=:store and (:email is null or u.adminEmail like %:email%)",
	    countQuery = "select count(distinct u) from User as u join u.groups ug join ug.permissions ugp join u.merchantStore um where um.code=:store and (:email is null or u.adminEmail like %:email%)")
	  Page<User> listByStore(@Param("store") String store, @Param("email") String email, Pageable pageable);
	
	  @Query(value = "select distinct u from User as u left join fetch u.groups ug left join fetch ug.permissions ugp left join fetch u.defaultLanguage ud join fetch u.merchantStore um where (:email is null or u.adminEmail like %:email%)",
		countQuery = "select count(distinct u) from User as u join u.groups ug join ug.permissions ugp join u.merchantStore um where (:email is null or u.adminEmail like %:email%)")
	  Page<User> listAll(@Param("email") String email, Pageable pageable);
	  
	  @Query(value = "select distinct u from User as u left join fetch u.groups ug left join fetch ug.permissions ugp left join fetch u.defaultLanguage ud join fetch u.merchantStore um where um.id in :stores and (:email is null or u.adminEmail like %:email%)",
		countQuery = "select count(distinct u) from User as u join u.groups ug join ug.permissions ugp join u.merchantStore um where um.id in :stores and (:email is null or u.adminEmail like %:email%)")
	  Page<User> listByStoreIds(@Param("stores") List<Integer> stores, @Param("email") String email, Pageable pageable);
	

}
