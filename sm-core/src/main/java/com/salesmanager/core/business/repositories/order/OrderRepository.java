package com.salesmanager.core.business.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.order.Order;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    @Query("select o from Order o join fetch o.merchant om "
    		+ "join fetch o.orderProducts op "
    		+ "left join fetch o.delivery od left join fetch od.country left join fetch od.zone "
    		+ "left join fetch o.billing ob left join fetch ob.country left join fetch ob.zone "
    		+ "left join fetch o.orderAttributes oa "
    		+ "join fetch o.orderTotal ot left "
    		+ "join fetch o.orderHistory oh left "
    		+ "join fetch op.downloads opd left "
    		+ "join fetch op.orderAttributes opa "
    		+ "left join fetch op.prices opp where o.id = :id and om.id = :merchantId")
	Order findOne(@Param("id") Long id, @Param("merchantId") Integer merchantId);
    
}
