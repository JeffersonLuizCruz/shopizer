package com.salesmanager.core.business.repositories.customer.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.customer.review.CustomerReview;
import org.springframework.data.repository.query.Param;

public interface CustomerReviewRepository extends JpaRepository<CustomerReview, Long> {
	
	String customerQuery = ""
			+ "select distinct r from CustomerReview r join fetch "
			+ "r.customer rc "
			//+ "join fetch rc.attributes rca left join "
			//+ "fetch rca.customerOption rcao left join fetch rca.customerOptionValue "
			//+ "rcav left join fetch rcao.descriptions rcaod left join fetch rcav.descriptions "
			+ "join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm "
			+ "left join fetch r.descriptions rd ";


	@Query("select r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where r.id = :id")
	CustomerReview findOne(@Param("id") Long id);
	
	@Query("select distinct r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where rc.id = :id")
	List<CustomerReview> findByReviewer(@Param("id") Long id);
	
	@Query("select distinct r from CustomerReview r join fetch r.customer rc join fetch r.reviewedCustomer rr join fetch rc.merchantStore rrm left join fetch r.descriptions rd where rr.id = :id")
	List<CustomerReview> findByReviewed(@Param("id") Long id);
	
	@Query( customerQuery + "where rc.id = :reviewer and rr.id = :reviewed")
	CustomerReview findByRevieweAndReviewed(@Param("reviewer") Long reviewer, @Param("reviewed") Long reviewed);

	
	
}
