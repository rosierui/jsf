package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the calendar_event database table.
 * 
 */
@Entity
@Table(name="calendar_event")
@NamedQuery(name="CalendarEvent.findAll", query="SELECT c FROM CalendarEvent c")
public class CalendarEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date day;

	@Column(name="end_time")
	private Time endTime;

	private String event;

	private String semester;

	@Column(name="start_time")
	private Time startTime;

	@Column(name="user_id")
	private int userId;

	private String week;

	public CalendarEvent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDay() {
		return this.day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}