package ua.java.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Results")
public class Result {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User rUser;
	private int questions;
	private int correctAnswer;
	private int mark;
	private boolean passed;
	@Column(length = 32, columnDefinition = "varchar(32) default 'passing'")
	@Enumerated(EnumType.STRING)
	private ResultStatus status;
	private int minMark;
	@ManyToOne
	@JoinColumn(name = "testId")
	private Test rTest;
	@OneToMany(mappedBy = "uaResult", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<UserAnswer> userAnswers = new ArrayList<UserAnswer>();
	@CreationTimestamp
	private Timestamp createdOn;
	@UpdateTimestamp
	private Timestamp updateDateTime;
	private Timestamp finishByUserDateTime;
	private Timestamp finalDateTime;

	public Timestamp getFinishByUserDateTime() {
		return finishByUserDateTime;
	}

	public void setFinishByUserDateTime(Timestamp finishByUserDateTime) {
		this.finishByUserDateTime = finishByUserDateTime;
	}

	public List<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(List<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	public int getQuestions() {
		return questions;
	}

	public void setQuestions(int questions) {
		this.questions = questions;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public User getrUser() {
		return rUser;
	}

	public void setrUser(User rUser) {
		this.rUser = rUser;
	}

	public Test getrTest() {
		return rTest;
	}

	public void setrTest(Test rTest) {
		this.rTest = rTest;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public int getMinMark() {
		return minMark;
	}

	public void setMinMark(int minMark) {
		this.minMark = minMark;
	}

	public Timestamp getFinalDateTime() {
		return finalDateTime;
	}

	public void setFinalDateTime(Timestamp finalDateTime) {
		this.finalDateTime = finalDateTime;
	}
}
