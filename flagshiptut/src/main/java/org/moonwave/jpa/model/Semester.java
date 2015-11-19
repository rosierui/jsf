package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the semester database table.
 * 
 */
@Entity
@Table(name="semester")
@NamedQuery(name="Semester.findAll", query="SELECT s FROM Semester s")
public class Semester implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String alias;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String name;

	@Column(name="number_of_weeks")
	private short numberOfWeeks;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	@Column(name="start_weekday")
	private String startWeekday;

	public Semester() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getNumberOfWeeks() {
		return this.numberOfWeeks;
	}

	public void setNumberOfWeeks(short numberOfWeeks) {
		this.numberOfWeeks = numberOfWeeks;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartWeekday() {
		return this.startWeekday;
	}

	public void setStartWeekday(String startWeekday) {
		this.startWeekday = startWeekday;
	}

}