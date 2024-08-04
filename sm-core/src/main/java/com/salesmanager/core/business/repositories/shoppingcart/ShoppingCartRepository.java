package com.salesmanager.core.business.repositories.shoppingcart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where c.id = :id")
	ShoppingCart findOne(@Param("id") Long id);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where c.shoppingCartCode = :code")
	ShoppingCart findByCode(@Param("code") String code);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where cm.id = :merchantId and c.id = :id")
	ShoppingCart findById(@Param("merchantId") Integer merchantId, @Param("id") Long id);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where cm.id = :merchantId and c.shoppingCartCode = :code")
	ShoppingCart findByCode(@Param("merchantId") Integer merchantId, @Param("code") String code);
	
	@Query("select c from ShoppingCart c left join fetch c.lineItems cl left join fetch cl.attributes cla join fetch c.merchantStore cm where c.customerId = :customerId")
	List<ShoppingCart> findByCustomer(@Param("customerId") Long customerId);
	
}
