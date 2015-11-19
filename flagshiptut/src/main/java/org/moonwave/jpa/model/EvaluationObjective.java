package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the evaluation_objective database table.
 * 
 */
@Entity
@Table(name="evaluation_objective")
@NamedQuery(name="EvaluationObjective.findAll", query="SELECT e FROM EvaluationObjective e")
public class EvaluationObjective implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="create_time")
	private Timestamp createTime;

	@Column(name="eval_part1_1")
	private byte evalPart11;

	@Column(name="eval_part1_2")
	private byte evalPart12;

	@Column(name="eval_part1_3")
	private byte evalPart13;

	@Column(name="eval_part1_comments")
	private String evalPart1Comments;

	@Column(name="eval_part2_1")
	private byte evalPart21;

	@Column(name="eval_part2_2")
	private byte evalPart22;

	@Column(name="eval_part2_3")
	private byte evalPart23;

	@Column(name="eval_part2_comments")
	private String evalPart2Comments;

	@Column(name="eval_part3_1")
	private byte evalPart31;

	@Column(name="eval_part3_2")
	private byte evalPart32;

	@Column(name="eval_part3_3")
	private byte evalPart33;

	@Column(name="eval_part3_comments")
	private String evalPart3Comments;

	private String semester;

	@Column(name="student_evaluation")
	private byte studentEvaluation;

	@Column(name="update_time")
	private Timestamp updateTime;

	@Column(name="user_id")
	private int userId;

	private String week;

	public EvaluationObjective() {
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

	public byte getEvalPart11() {
		return this.evalPart11;
	}

	public void setEvalPart11(byte evalPart11) {
		this.evalPart11 = evalPart11;
	}

	public byte getEvalPart12() {
		return this.evalPart12;
	}

	public void setEvalPart12(byte evalPart12) {
		this.evalPart12 = evalPart12;
	}

	public byte getEvalPart13() {
		return this.evalPart13;
	}

	public void setEvalPart13(byte evalPart13) {
		this.evalPart13 = evalPart13;
	}

	public String getEvalPart1Comments() {
		return this.evalPart1Comments;
	}

	public void setEvalPart1Comments(String evalPart1Comments) {
		this.evalPart1Comments = evalPart1Comments;
	}

	public byte getEvalPart21() {
		return this.evalPart21;
	}

	public void setEvalPart21(byte evalPart21) {
		this.evalPart21 = evalPart21;
	}

	public byte getEvalPart22() {
		return this.evalPart22;
	}

	public void setEvalPart22(byte evalPart22) {
		this.evalPart22 = evalPart22;
	}

	public byte getEvalPart23() {
		return this.evalPart23;
	}

	public void setEvalPart23(byte evalPart23) {
		this.evalPart23 = evalPart23;
	}

	public String getEvalPart2Comments() {
		return this.evalPart2Comments;
	}

	public void setEvalPart2Comments(String evalPart2Comments) {
		this.evalPart2Comments = evalPart2Comments;
	}

	public byte getEvalPart31() {
		return this.evalPart31;
	}

	public void setEvalPart31(byte evalPart31) {
		this.evalPart31 = evalPart31;
	}

	public byte getEvalPart32() {
		return this.evalPart32;
	}

	public void setEvalPart32(byte evalPart32) {
		this.evalPart32 = evalPart32;
	}

	public byte getEvalPart33() {
		return this.evalPart33;
	}

	public void setEvalPart33(byte evalPart33) {
		this.evalPart33 = evalPart33;
	}

	public String getEvalPart3Comments() {
		return this.evalPart3Comments;
	}

	public void setEvalPart3Comments(String evalPart3Comments) {
		this.evalPart3Comments = evalPart3Comments;
	}

	public String getSemester() {
		return this.semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public byte getStudentEvaluation() {
		return this.studentEvaluation;
	}

	public void setStudentEvaluation(byte studentEvaluation) {
		this.studentEvaluation = studentEvaluation;
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

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}