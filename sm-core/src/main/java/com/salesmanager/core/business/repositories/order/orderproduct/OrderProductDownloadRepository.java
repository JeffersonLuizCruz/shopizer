package com.salesmanager.core.business.repositories.order.orderproduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.order.orderproduct.OrderProductDownload;
import org.springframework.data.repository.query.Param;

public interface OrderProductDownloadRepository extends JpaRepository<OrderProductDownload, Long> {

	@Query("select o from OrderProductDownload o left join fetch o.orderProduct op join fetch op.order opo join fetch opo.merchant opon where o.id = :id")
	OrderProductDownload findOne(@Param("id") Long id);
	
	@Query("select o from OrderProductDownload o left join fetch o.orderProduct op join fetch op.order opo join fetch opo.merchant opon where opo.id = :id")
	List<OrderProductDownload> findByOrderId( @Param("id") Long id);

}
