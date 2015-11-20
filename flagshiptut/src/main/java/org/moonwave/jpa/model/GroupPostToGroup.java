package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the group_post_to_group database table.
 * 
 */
@Entity
@Table(name="group_post_to_group")
@NamedQuery(name="GroupPostToGroup.findAll", query="SELECT g FROM GroupPostToGroup g")
public class GroupPostToGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="tutor_group_id")
	private short tutorGroupId;

	//bi-directional many-to-one association to GroupPost
	@ManyToOne
	@JoinColumn(name="group_post_id")
	private GroupPost groupPost;

	public GroupPostToGroup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getTutorGroupId() {
		return this.tutorGroupId;
	}

	public void setTutorGroupId(short tutorGroupId) {
		this.tutorGroupId = tutorGroupId;
	}

	public GroupPost getGroupPost() {
		return this.groupPost;
	}

	public void setGroupPost(GroupPost groupPost) {
		this.groupPost = groupPost;
	}

}