package com.ciandt.olympics.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.ciandt.olympics.model.enums.Phase;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CompetitionDto {
	
	@NotNull(message="ModalityId is required")
	private Long modalityId;
	
	@NotNull(message="LocalId is required")
	private Long localId;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="Date is required")
	@Column(columnDefinition="DATE")
	private LocalDate date;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull(message="Start Time is required")
	@Column(columnDefinition="TIME")
	private LocalTime timeStart;
	
	@JsonFormat(pattern = "HH:mm")
	@NotNull(message="End Time is required")
	@Column(columnDefinition="TIME")
	private LocalTime timeEnd;
	
	@NotNull(message="CountryId1 is required")
	private Long countryId1;
	
	@NotNull(message="CountryId2 is required")
	private Long countryId2;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Phase is required")
	private Phase phase;
	
	public CompetitionDto() { }

	public CompetitionDto(Long modalityId, Long localId, LocalDate date, LocalTime timeStart, LocalTime timeEnd,
			Long countryId1, Long countryId2, Phase phase) {
		this.modalityId = modalityId;
		this.localId = localId;
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.countryId1 = countryId1;
		this.countryId2 = countryId2;
		this.phase = phase;
	}

	public Long getModalityId() {
		return modalityId;
	}

	public void setModalityId(Long modalityId) {
		this.modalityId = modalityId;
	}

	public Long getLocalId() {
		return localId;
	}

	public void setLocalId(Long localId) {
		this.localId = localId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Long getCountryId1() {
		return countryId1;
	}

	public void setCountryId1(Long countryId1) {
		this.countryId1 = countryId1;
	}

	public Long getCountryId2() {
		return countryId2;
	}

	public void setCountryId2(Long countryId2) {
		this.countryId2 = countryId2;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
}
