package com.ciandt.olympics.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ciandt.olympics.model.Modality;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

	@Modifying
	@Transactional
	@Query("delete from Modality m where m.id=:id")
	int deleteById(@Param("id") Long id);
	
}
