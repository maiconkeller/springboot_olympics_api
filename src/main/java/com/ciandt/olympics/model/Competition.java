package com.ciandt.olympics.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ciandt.olympics.model.enums.Phase;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="competitions")
public class Competition {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable=false, name="modality_id")
	@NotNull(message="Modality is required")
	private Modality modality;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="local_id")
	@NotNull(message="Local is required")
	private Local local;
	
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
	
	@ManyToOne
	@JoinColumn(nullable=false, name="country1_id")
	@NotNull(message="Country1 is required")
	private Country country1;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="country2_id")
	@NotNull(message="Country2 is required")
	private Country country2;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="Phase is required")
	private Phase phase;

	public Competition() { }
	
	public Competition(Long modalityId, Long localId, LocalDate date, LocalTime timeStart, LocalTime timeEnd,
			Long countryId1, Long countryId2, Phase phase) {
		super();
		this.modality = new Modality(modalityId);
		this.local = new Local(localId);
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.country1 = new Country(countryId1);
		this.country2 = new Country(countryId2);
		this.phase = phase;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modality getModality() {
		return modality;
	}

	public void setModality(Modality modality) {
		this.modality = modality;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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

	public Country getCountry1() {
		return country1;
	}

	public void setCountry1(Country country1) {
		this.country1 = country1;
	}

	public Country getCountry2() {
		return country2;
	}

	public void setCountry2(Country country2) {
		this.country2 = country2;
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country1 == null) ? 0 : country1.hashCode());
		result = prime * result + ((country2 == null) ? 0 : country2.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result + ((modality == null) ? 0 : modality.hashCode());
		result = prime * result + ((phase == null) ? 0 : phase.hashCode());
		result = prime * result + ((timeEnd == null) ? 0 : timeEnd.hashCode());
		result = prime * result + ((timeStart == null) ? 0 : timeStart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Competition other = (Competition) obj;
		if (country1 == null) {
			if (other.country1 != null)
				return false;
		} else if (!country1.equals(other.country1))
			return false;
		if (country2 == null) {
			if (other.country2 != null)
				return false;
		} else if (!country2.equals(other.country2))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (modality == null) {
			if (other.modality != null)
				return false;
		} else if (!modality.equals(other.modality))
			return false;
		if (phase != other.phase)
			return false;
		if (timeEnd == null) {
			if (other.timeEnd != null)
				return false;
		} else if (!timeEnd.equals(other.timeEnd))
			return false;
		if (timeStart == null) {
			if (other.timeStart != null)
				return false;
		} else if (!timeStart.equals(other.timeStart))
			return false;
		return true;
	}
	
}
