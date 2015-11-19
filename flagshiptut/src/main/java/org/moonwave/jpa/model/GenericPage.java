package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the generic_page database table.
 * 
 */
@Entity
@Table(name="generic_page")
@NamedQuery(name="GenericPage.findAll", query="SELECT g FROM GenericPage g")
public class GenericPage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private byte active;

	@Lob
	private String content;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="page_key")
	private String pageKey;

	@Temporal(TemporalType.DATE)
	@Column(name="publish_date")
	private Date publishDate;

	@Column(name="show_subject")
	private byte showSubject;

	private String subject;

	@Column(name="update_time")
	private Timestamp updateTime;

	public GenericPage() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPageKey() {
		return this.pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public byte getShowSubject() {
		return this.showSubject;
	}

	public void setShowSubject(byte showSubject) {
		this.showSubject = showSubject;
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

}