package com.salesmanager.core.business.repositories.order;

import com.salesmanager.core.model.order.orderstatus.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Long> {

    @Query("select osh from OrderStatusHistory osh" +
            " join fetch osh.order o" +
            " where o.id = :id order by osh.dateAdded desc")
    List<OrderStatusHistory> findByOrderId(@Param("id") Long id);
}
