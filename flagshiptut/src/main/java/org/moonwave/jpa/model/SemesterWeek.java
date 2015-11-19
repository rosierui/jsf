package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the semester_week database table.
 * 
 */
@Entity
@Table(name="semester_week")
@NamedQuery(name="SemesterWeek.findAll", query="SELECT s FROM SemesterWeek s")
public class SemesterWeek implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String semester;

	@Temporal(TemporalType.DATE)
	@Column(name="start_day")
	private Date startDay;

	private String week;

	public SemesterWeek() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Date getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}