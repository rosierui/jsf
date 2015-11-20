package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the group_post database table.
 * 
 */
@Entity
@Table(name="group_post")
@NamedQuery(name="GroupPost.findAll", query="SELECT g FROM GroupPost g")
public class GroupPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String body;

	@Column(name="create_time")
	private Timestamp createTime;

	private byte published;

	private String subject;

	@Column(name="update_time")
	private Timestamp updateTime;

	//bi-directional many-to-one association to GroupPostToGroup
	@OneToMany(mappedBy="groupPost")
	private List<GroupPostToGroup> groupPostToGroups;

	public GroupPost() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte getPublished() {
		return this.published;
	}

	public void setPublished(byte published) {
		this.published = published;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public List<GroupPostToGroup> getGroupPostToGroups() {
		return this.groupPostToGroups;
	}

	public void setGroupPostToGroups(List<GroupPostToGroup> groupPostToGroups) {
		this.groupPostToGroups = groupPostToGroups;
	}

	public GroupPostToGroup addGroupPostToGroup(GroupPostToGroup groupPostToGroup) {
		getGroupPostToGroups().add(groupPostToGroup);
		groupPostToGroup.setGroupPost(this);

		return groupPostToGroup;
	}

	public GroupPostToGroup removeGroupPostToGroup(GroupPostToGroup groupPostToGroup) {
		getGroupPostToGroups().remove(groupPostToGroup);
		groupPostToGroup.setGroupPost(null);

		return groupPostToGroup;
	}

}