package com.ciandt.olympics.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ciandt.olympics.model.Competition;
import com.ciandt.olympics.model.Modality;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	@Modifying
	@Transactional
	@Query("delete from Competition c where c.id=:id")
	int deleteById(@Param("id") Long id);

	@Transactional
	@Query("select c from Competition c where c.modality=:modality order by c.date, c.timeStart")
	List<Competition> findByModality(@Param("modality") Modality modality);
	
}
