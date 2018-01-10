package com.ciandt.olympics.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ciandt.olympics.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long> {

	@Modifying
	@Transactional
	@Query("delete from Local l where l.id=:id")
	int deleteById(@Param("id") Long id);
	
}
