package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the week database table.
 * 
 */
@Entity
@Table(name="week")
@NamedQuery(name="Week.findAll", query="SELECT w FROM Week w")
public class Week implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private short id;

    private String week;

    public Week() {
    }

    public short getId() {
        return this.id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public int hashCode() {
        return ((Short)id).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof Week)) {
            return false;
        }
        Week other = (Week) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",week= ").append(week);
        return sb.toString();
    }
}