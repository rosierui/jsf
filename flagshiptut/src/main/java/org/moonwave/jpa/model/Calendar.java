package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the calendar database table.
 * 
 */
@Entity
@Table(name="calendar")

@NamedQueries({
    @NamedQuery(name="Calendar.findAll",  query="SELECT c FROM Calendar c"),
    @NamedQuery(name="Calendar.findById", query="SELECT c FROM Calendar c WHERE c.id = :id")
})

public class Calendar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String semester;

    private String week;

    public Calendar() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSemester() {
        return this.semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
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
        if ((o == null) || !(o instanceof Calendar)) {
            return false;
        }
        Calendar other = (Calendar) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",date= ").append(date);
        sb.append(",semester= ").append(semester);
        sb.append(",week= ").append(week);
        return sb.toString();
    }
}