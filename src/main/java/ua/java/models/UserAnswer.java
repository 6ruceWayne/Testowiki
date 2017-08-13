package ua.java.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "userAnswers")
public class UserAnswer {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "resultId")
	private Result uaResult;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User aUser;
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question uaQuestion;
	private boolean correct;
	@CreationTimestamp
	private Timestamp createdOn;
	@UpdateTimestamp
	private Timestamp updateDateTime;

	public Result getUaResult() {
		return uaResult;
	}

	public void setUaResult(Result uaResult) {
		this.uaResult = uaResult;
	}

	public User getaUser() {
		return aUser;
	}

	public void setaUser(User aUser) {
		this.aUser = aUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getUaQuestion() {
		return uaQuestion;
	}

	public void setUaQuestion(Question uaQuestion) {
		this.uaQuestion = uaQuestion;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
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

}
