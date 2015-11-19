package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the course_reg database table.
 * 
 */
@Entity
@Table(name="course_reg")
@NamedQuery(name="CourseReg.findAll", query="SELECT c FROM CourseReg c")
public class CourseReg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="course_id")
	private short courseId;

	@Column(name="create_time")
	private Timestamp createTime;

	@Temporal(TemporalType.DATE)
	@Column(name="reg_date")
	private Date regDate;

	@Column(name="student_id")
	private int studentId;

	@Column(name="update_time")
	private Timestamp updateTime;

	public CourseReg() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getCourseId() {
		return this.courseId;
	}

	public void setCourseId(short courseId) {
		this.courseId = courseId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}