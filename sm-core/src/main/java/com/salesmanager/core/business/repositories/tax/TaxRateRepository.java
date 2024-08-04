package com.salesmanager.core.business.repositories.tax;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesmanager.core.model.tax.taxrate.TaxRate;
import org.springframework.data.repository.query.Param;

public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {

	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country left join fetch t.zone left join fetch t.descriptions left join t.parent where tm.id=:id order by t.taxPriority asc")
	List<TaxRate> findByStore(@Param("id") Integer id);
	
	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country left join fetch t.zone left join fetch t.descriptions td left join t.parent where tm.id=:id and td.language.id=:languageId order by t.taxPriority asc")
	List<TaxRate> findByStoreAndLanguage(@Param("id") Integer id, @Param("languageId") Integer languageId);
	
	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country left join fetch t.zone left join fetch t.descriptions td left join t.parent where tm.id=:id and t.code=:code")
	TaxRate findByStoreAndCode(@Param("id") Integer id, @Param("code") String code);
	
	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country left join fetch t.zone left join fetch t.descriptions td left join t.parent where tm.id=:store and t.id=:id")
	TaxRate findByStoreAndId(@Param("store") Integer store, @Param("id") Long id);
	
	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country left join fetch t.zone left join fetch t.descriptions td left join t.parent where t.id=:id")
	TaxRate findOne(@Param("id") Long id);
	
	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country tc left join fetch t.zone tz left join fetch t.descriptions td left join t.parent where tm.id=:id AND (tz.id=:zoneId OR tz IS NULL) and tc.id=:countryId and td.language.id=:languageId order by t.taxPriority asc")
	List<TaxRate> findByMerchantAndZoneAndCountryAndLanguage(@Param("id") Integer id, @Param("zoneId") Long zoneId, @Param("countryId") Integer countryId, @Param("languageId") Integer languageId);
	
	@Query("select t from TaxRate t join fetch t.taxClass join fetch t.merchantStore tm join fetch t.country tc left join fetch t.zone tz left join fetch t.descriptions td left join t.parent where tm.id=:id AND t.stateProvince=:province and tc.id=:countryId and td.language.id=:languageId order by t.taxPriority asc")
	List<TaxRate> findByMerchantAndProvinceAndCountryAndLanguage(@Param("id") Integer id, @Param("province") String province, @Param("countryId") Integer countryId, @Param("languageId") Integer languageId);
	
	
}
