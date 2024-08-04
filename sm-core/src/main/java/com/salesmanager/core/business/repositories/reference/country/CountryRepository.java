package com.salesmanager.core.business.repositories.reference.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.reference.country.Country;
import org.springframework.data.repository.query.Param;


public interface CountryRepository extends JpaRepository <Country, Integer> {
	
	@Query("select c from Country c left join fetch c.descriptions cd where c.isoCode=:code")
	Country findByIsoCode(@Param("code") String code);
	

	@Query("select c from Country c "
			+ "left join fetch c.descriptions cd "
			+ "left join fetch c.zones cz left join fetch cz.descriptions "
			+ "where cd.language.id=:id")
	List<Country> listByLanguage(@Param("id") Integer id);
	
	/** get country including zones by language **/
	@Query("select distinct c from Country c left join fetch c.descriptions cd left join fetch c.zones cz left join fetch cz.descriptions where cd.language.id=:id")
	List<Country> listCountryZonesByLanguage(@Param("id") Integer id);

}
