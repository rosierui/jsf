package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_tutor_group database table.
 * 
 */
@Entity
@Table(name="user_tutor_group")
@NamedQuery(name="UserTutorGroup.findAll", query="SELECT u FROM UserTutorGroup u")
public class UserTutorGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="create_time")
	private Timestamp createTime;

	private String privileges;

	@Column(name="tutor_group_id")
	private short tutorGroupId;

	@Column(name="update_time")
	private Timestamp updateTime;

	@Column(name="user_id")
	private int userId;

	public UserTutorGroup() {
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

	public String getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public short getTutorGroupId() {
		return this.tutorGroupId;
	}

	public void setTutorGroupId(short tutorGroupId) {
		this.tutorGroupId = tutorGroupId;
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

}