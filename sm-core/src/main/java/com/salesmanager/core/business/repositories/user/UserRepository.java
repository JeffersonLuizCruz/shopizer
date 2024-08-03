package com.salesmanager.core.business.repositories.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.user.User;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	@Query("select distinct u from User as u left join fetch u.groups ug left join fetch ug.permissions ugp join fetch u.merchantStore um left join fetch u.defaultLanguage ul where u.adminName = :username")
	User findByUserName(@Param("username") String username);
	
	@Query("select distinct u from User as u left join fetch u.groups ug join fetch u.merchantStore um left join fetch u.defaultLanguage ul where u.id = :userId and um.code = :storecode")
	User findByUserId(@Param("userId") Long userId, @Param("storecode") String storecode);
	
	@Query("select distinct u from User as u left join fetch u.groups ug join fetch u.merchantStore um left join fetch u.defaultLanguage ul where u.adminName= :username and um.code = :storecode")
	User findByUserName(@Param("username") String username, @Param("storecode") String storecode);

	@Query("select distinct u from User as u left join fetch u.groups ug join fetch u.merchantStore um left join fetch u.defaultLanguage ul where u.id = :id")
	User findOne(@Param("id") Long id);
	
	@Query("select distinct u from User as u left join fetch u.groups ug join fetch u.merchantStore um left join fetch u.defaultLanguage ul order by u.id")
	List<User> findAll();
	
	@Query("select distinct u from User as u left join fetch u.groups ug join fetch u.merchantStore um left join fetch u.defaultLanguage ul where um.id = :storeId order by u.id")
	List<User> findByStore(@Param("storeId") Integer storeId);
	
	@Query("select distinct u from User as u left join fetch u.groups ug join fetch u.merchantStore um left join fetch u.defaultLanguage ul where u.id= :userId and um.code = :storecode")
	User findByUserAndStore(@Param("userId") Long userId, @Param("storecode") String storecode);

	@Query("select distinct u from User as u "
			+ "left join fetch u.groups ug "
			+ "join fetch u.merchantStore um "
			+ "left join fetch u.defaultLanguage ul "
			+ "where u.credentialsResetRequest.credentialsRequest = :token and um.code = :store ")
	User findByResetPasswordToken(@Param("token") String token, @Param("store") String store);
}
