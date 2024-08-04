package com.salesmanager.core.business.repositories.tax;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.tax.taxclass.TaxClass;
import org.springframework.data.repository.query.Param;

public interface TaxClassRepository extends JpaRepository<TaxClass, Long> {
	
	@Query("select t from TaxClass t left join fetch t.merchantStore tm where tm.id=:id")
	List<TaxClass> findByStore(@Param("id") Integer id);
	
	@Query("select t from TaxClass t left join fetch t.merchantStore tm where t.code=:code")
	TaxClass findByCode(@Param("code") String code);
	
	@Query("select t from TaxClass t left join fetch t.merchantStore tm where tm.id=:id and t.code=:code")
	TaxClass findByStoreAndCode(@Param("id") Integer id, @Param("code") String code);


}
