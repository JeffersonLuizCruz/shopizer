package com.salesmanager.core.business.repositories.catalog.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.catalog.catalog.Catalog;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
	
	
	@Query("select c from Catalog c "
			+ "join c.merchantStore cm "
			+ "left join fetch c.entry ce "
			//+ "left join fetch ce.product cep "
			+ "left join fetch ce.category cec where c.id=:catalogId and cm.id = :merchantId")
	Optional<Catalog> findById(@Param("catalogId") Long catalogId, @Param("merchantId") Integer merchantId);
	
	@Query("select c from Catalog c "
			+ "join c.merchantStore cm "
			+ "left join fetch c.entry ce "
			//+ "left join fetch ce.product cep "
			+ "left join fetch ce.category cec where c.code=:code and cm.id = :merchantId")
	Optional<Catalog> findByCode(@Param("code") String code, @Param("merchantId") Integer merchantId);
	
	@Query("SELECT COUNT(c) > 0 FROM Catalog c "
			+ "join c.merchantStore cm  "
			+ "WHERE c.code = :code and cm.id = :merchantId")
	boolean existsByCode(@Param("code") String code, @Param("merchantId") Integer merchantId);

}
