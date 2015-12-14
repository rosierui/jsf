package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the evaluation_performance database table.
 * 
 */
@Entity
@Table(name="evaluation_performance")

@NamedQueries({
    @NamedQuery(name="EvaluationPerformance.findAll",  query="SELECT e FROM EvaluationPerformance e"),
    @NamedQuery(name="EvaluationPerformance.findById", query="SELECT e FROM EvaluationPerformance e WHERE e.id = :id")
})

public class EvaluationPerformance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private short attendance;

    @Column(name="create_time")
    private Timestamp createTime;

    private String note;

    private short participation;

    private short performance;

    private String semester;

    private short total;

    @Column(name="update_time")
    private Timestamp updateTime;

    private String week;

    public EvaluationPerformance() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getAttendance() {
        return this.attendance;
    }

    public void setAttendance(short attendance) {
        this.attendance = attendance;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public short getParticipation() {
        return this.participation;
    }

    public void setParticipation(short participation) {
        this.participation = participation;
    }

    public short getPerformance() {
        return this.performance;
    }

    public void setPerformance(short performance) {
        this.performance = performance;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public short getTotal() {
        return this.total;
    }

    public void setTotal(short toatl) {
        this.total = toatl;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof EvaluationPerformance)) {
            return false;
        }
        EvaluationPerformance other = (EvaluationPerformance) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",attendance= ").append(attendance);
        sb.append(",participation= ").append(participation);
        sb.append(",performance= ").append(performance);
        sb.append(",total= ").append(total);
        return sb.toString();
    }

}