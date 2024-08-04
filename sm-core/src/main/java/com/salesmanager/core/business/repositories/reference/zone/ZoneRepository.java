package com.salesmanager.core.business.repositories.reference.zone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.reference.zone.Zone;
import org.springframework.data.repository.query.Param;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
	
	
	Zone findByCode(String code);
	
	@Query("select z from Zone z left join fetch z.descriptions zd where zd.language.id=:id")
	List<Zone> listByLanguage(@Param("id") Integer id);
	
	@Query("select z from Zone z left join fetch z.descriptions zd join fetch z.country zc where zc.isoCode=:isoCode and zd.language.id=:languageId")
	List<Zone> listByLanguageAndCountry(@Param("isoCode") String isoCode, @Param("languageId") Integer languageId);

}
