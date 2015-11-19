package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the system_info database table.
 * 
 */
@Entity
@Table(name="system_info")
@NamedQuery(name="SystemInfo.findAll", query="SELECT s FROM SystemInfo s")
public class SystemInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short id;

	private byte active;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="functional_locking")
	private byte functionalLocking;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="locking_kick_in_time")
	private Date lockingKickInTime;

	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="msg_kick_in_time")
	private Date msgKickInTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="msg_kick_out_time")
	private Date msgKickOutTime;

	private String severity;

	@Temporal(TemporalType.DATE)
	@Column(name="system_info_date")
	private Date systemInfoDate;

	@Column(name="update_time")
	private Timestamp updateTime;

	public SystemInfo() {
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

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte getFunctionalLocking() {
		return this.functionalLocking;
	}

	public void setFunctionalLocking(byte functionalLocking) {
		this.functionalLocking = functionalLocking;
	}

	public Date getLockingKickInTime() {
		return this.lockingKickInTime;
	}

	public void setLockingKickInTime(Date lockingKickInTime) {
		this.lockingKickInTime = lockingKickInTime;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMsgKickInTime() {
		return this.msgKickInTime;
	}

	public void setMsgKickInTime(Date msgKickInTime) {
		this.msgKickInTime = msgKickInTime;
	}

	public Date getMsgKickOutTime() {
		return this.msgKickOutTime;
	}

	public void setMsgKickOutTime(Date msgKickOutTime) {
		this.msgKickOutTime = msgKickOutTime;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Date getSystemInfoDate() {
		return this.systemInfoDate;
	}

	public void setSystemInfoDate(Date systemInfoDate) {
		this.systemInfoDate = systemInfoDate;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}