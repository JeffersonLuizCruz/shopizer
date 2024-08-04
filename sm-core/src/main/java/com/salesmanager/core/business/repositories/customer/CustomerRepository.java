package com.salesmanager.core.business.repositories.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.Customer;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {

	
	@Query("select c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups where c.id = :id")
	Customer findOne(@Param("id") Long id);
	
	@Query("select distinct c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where c.billing.firstName = :name")
	List<Customer> findByName(@Param("name") String name);
	
	@Query("select c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where c.nick = :nick")
	Customer findByNick(@Param("nick") String nick);
	
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.nick = :nick and cm.id = :storeId")
	Customer findByNick(@Param("nick") String nick, @Param("storeId") int storeId);
	
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.nick = :nick and cm.code = :store")
	Customer findByNick(@Param("nick") String nick, @Param("store") String store);
	
	@Query("select c from Customer c "
			+ "join fetch c.merchantStore cm "
			+ "left join fetch c.defaultLanguage cl "
			+ "left join fetch c.attributes ca "
			+ "left join fetch ca.customerOption cao "
			+ "left join fetch ca.customerOptionValue cav "
			+ "left join fetch cao.descriptions caod "
			+ "left join fetch cav.descriptions  "
			+ "left join fetch c.groups  "
			+ "left join fetch c.delivery cd "
			+ "left join fetch c.billing cb "
			+ "left join fetch cd.country "
			+ "left join fetch cd.zone "
			+ "left join fetch cb.country "
			+ "left join fetch cb.zone "
			+ "where c.credentialsResetRequest.credentialsRequest = :token and cm.code = :store")
	Customer findByResetPasswordToken(@Param("token") String token, @Param("store") String store);
	
	@Query("select distinct c from Customer c join fetch c.merchantStore cm left join fetch c.defaultLanguage cl left join fetch c.attributes ca left join fetch ca.customerOption cao left join fetch ca.customerOptionValue cav left join fetch cao.descriptions caod left join fetch cav.descriptions left join fetch c.groups  where cm.id = :storeId")
	List<Customer> findByStore(@Param("storeId") int storeId);
	

}
