package com.ciandt.olympics.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ciandt.olympics.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findByCode(String code);

	@Modifying
	@Transactional
	@Query("delete from Country c where c.code=:code ")
	int deleteByCode(@Param("code") String code);

}
