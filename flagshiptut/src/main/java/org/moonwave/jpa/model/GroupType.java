package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the group_type database table.
 * 
 */
@Entity
@Table(name="group_type")
@NamedQuery(name="GroupType.findAll", query="SELECT g FROM GroupType g")
public class GroupType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String alias;

	private String name;

	public GroupType() {
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

}