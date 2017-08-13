package ua.java.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "MiddleResults")
public class MiddleResult {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int middleCorrectAnswer;
	private int totalCorrectAnswers;
	private int middleMark;
	private int totalMarks;
	private int passedUser;
	private int amountExam;
	@OneToOne
	@JoinColumn(name = "testId")
	private Test mTest;
	@CreationTimestamp
	private Timestamp createdOn;
	@UpdateTimestamp
	private Timestamp updateDateTime;

	public Test getmTest() {
		return mTest;
	}

	public void setmTest(Test mTest) {
		this.mTest = mTest;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMiddleCorrectAnswer() {
		return middleCorrectAnswer;
	}

	public void setMiddleCorrectAnswer(int middleCorrectAnswer) {
		this.middleCorrectAnswer = middleCorrectAnswer;
	}

	public int getTotalCorrectAnswers() {
		return totalCorrectAnswers;
	}

	public void setTotalCorrectAnswers(int totalCorrectAnswers) {
		this.totalCorrectAnswers = totalCorrectAnswers;
	}

	public int getMiddleMark() {
		return middleMark;
	}

	public void setMiddleMark(int middleMark) {
		this.middleMark = middleMark;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getPassedUser() {
		return passedUser;
	}

	public void setPassedUser(int passedUser) {
		this.passedUser = passedUser;
	}

	public int getAmountExam() {
		return amountExam;
	}

	public void setAmountExam(int amountExam) {
		this.amountExam = amountExam;
	}

	public void calcMiddleResults() {
		calcMiddleCorrectAnswer();
		calcMiddleMark();
	}

	private void calcMiddleMark() {
		middleMark = totalMarks / amountExam;
	}

	private void calcMiddleCorrectAnswer() {
		middleCorrectAnswer = totalCorrectAnswers / amountExam;
	}

	public void addTotalMark(int userMark) {
		totalMarks = totalMarks + userMark;
	}

	public void addTotalCorrectAnswer(int userCorrectAnswers) {
		totalCorrectAnswers = totalCorrectAnswers + userCorrectAnswers;
	}

	public void incrementPassedUsers() {
		passedUser = passedUser + 1;
	}
}
