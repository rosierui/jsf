package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;


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

    @Column(name="create_time")
    private Timestamp createTime;

    @Temporal(TemporalType.DATE)
    private Date day;

    @Column(name="end_time")
    private Time endTime;

    private String event;

    private String semester;

    @Column(name="start_time")
    private Time startTime;

    @Column(name="update_time")
    private Timestamp updateTime;

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

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public int hashCode() {
        return ((Integer)id).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof CalendarEvent)) {
            return false;
        }
        CalendarEvent other = (CalendarEvent) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",event= ").append(event);
        return sb.toString();
    }
}