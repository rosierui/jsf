package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tutor_group database table.
 * 
 */
@Entity
@Table(name="tutor_group")
@NamedQuery(name="TutorGroup.findAll", query="SELECT t FROM TutorGroup t")
public class TutorGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String alias;

	private String name;

	private short ordinal;

	public TutorGroup() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(short ordinal) {
		this.ordinal = ordinal;
	}

}