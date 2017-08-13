package ua.java.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Articles")
public class Article {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String content;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User arUser;
	@OneToOne
	@JoinColumn(name = "testId")
	private Test arTest;
	@CreationTimestamp
	private Timestamp createdOn;
	@UpdateTimestamp
	private Timestamp updateDateTime;

	public User getArUser() {
		return arUser;
	}

	public void setArUser(User arUser) {
		this.arUser = arUser;
	}

	public Test getArTest() {
		return arTest;
	}

	public void setArTest(Test arTest) {
		this.arTest = arTest;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
