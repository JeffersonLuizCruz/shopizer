package com.salesmanager.core.business.repositories.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.salesmanager.core.model.shoppingcart.ShoppingCartItem;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
  
  @Query("select i from ShoppingCartItem i left join fetch i.attributes ia where i.id = :id")
  ShoppingCartItem findOne(@Param("id") Long id);
  
  @Modifying
  @Query("delete from ShoppingCartItem i where i.id = :id")
  void deleteById(@Param("id") Long id);


}
