package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the schedule database table.
 * 
 */
@Entity
@Table(name="schedule")

@NamedQueries({
    @NamedQuery(name="Schedule.findAll",  query="SELECT s FROM Schedule s"),
    @NamedQuery(name="Schedule.findById", query="SELECT s FROM Schedule s WHERE s.id = :id"),
    @NamedQuery(name="Schedule.findByUserId", query="SELECT s FROM Schedule s WHERE s.userId = :userId"),
    @NamedQuery(name="Schedule.findByTutorId", query="SELECT s FROM Schedule s WHERE s.tutorId = :tutorId")
})

public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="tutor_id")
    private Integer tutorId;

    private String event;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_time")
    private Date endTime;

    @Column(name="all_day_event")
    private Boolean allDayEvent;

    @Column(name="update_time")
    private Timestamp updateTime;

    @Column(name="create_time")
    private Timestamp createTime;

    public Schedule() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getAllDayEvent() {
        return allDayEvent;
    }

    public void setAllDayEvent(Boolean allDayEvent) {
        this.allDayEvent = allDayEvent;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) o;
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