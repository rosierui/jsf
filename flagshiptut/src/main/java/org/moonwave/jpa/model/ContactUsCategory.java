package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the contact_us_category database table.
 * 
 */
@Entity
@Table(name="contact_us_category")
@NamedQuery(name="ContactUsCategory.findAll", query="SELECT c FROM ContactUsCategory c")
public class ContactUsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private String category;

	@Column(name="create_time")
	private Timestamp createTime;

	private String email;

	private short ordinal;

	@Column(name="update_time")
	private Timestamp updateTime;

	public ContactUsCategory() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getOrdinal() {
		return this.ordinal;
	}

	public void setOrdinal(short ordinal) {
		this.ordinal = ordinal;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}