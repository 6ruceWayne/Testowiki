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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Tests")
public class Test {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private int count;
	@Column(columnDefinition = "TEXT")
	private String description;
	private boolean free;
	private int price;
	private int minSuccessMark;
	@Column(length = 32, columnDefinition = "varchar(32) default 'Russian'")
	@Enumerated(EnumType.STRING)
	private LocaleLanguage locale;
	@ManyToOne
	@JoinColumn(name = "authorId")
	private User tAuthor;
	@OneToOne(mappedBy = "arTest", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Article article;
	@ManyToOne
	@JoinColumn(name = "sectionId")
	private Section sectionEntity;
	@OneToMany(mappedBy = "rTest", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Result> results = new ArrayList<Result>();
	@OneToOne(mappedBy = "mTest", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private MiddleResult middleResult;
	@OneToMany(mappedBy = "qTest", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Question> questions = new ArrayList<Question>();
	@Column(columnDefinition = "TEXT")
	private String commentToAdmin;
	@Column(columnDefinition = "TEXT")
	private String responseForUser;
	@Column(length = 32, columnDefinition = "varchar(32) default 'Developing'")
	@Enumerated(EnumType.STRING)
	private TestStatus status = TestStatus.Developing;
	private long time;
	@CreationTimestamp
	private Timestamp createdOn;
	@UpdateTimestamp
	private Timestamp updateDateTime;

	public Test() {

	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public MiddleResult getMiddleResult() {
		return middleResult;
	}

	public void setMiddleResult(MiddleResult middleResult) {
		this.middleResult = middleResult;
	}

	public LocaleLanguage getLocale() {
		return locale;
	}

	public void setLocale(LocaleLanguage locale) {
		this.locale = locale;
	}

	public Section getSectionEntity() {
		return sectionEntity;
	}

	public void setSectionEntity(Section sectionEntity) {
		this.sectionEntity = sectionEntity;
	}

	public Timestamp getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Timestamp updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public List<Result> getResult() {
		return results;
	}

	public void setResult(List<Result> result) {
		this.results = result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public User gettAuthor() {
		return tAuthor;
	}

	public void settAuthor(User tAuthor) {
		this.tAuthor = tAuthor;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getCommentToAdmin() {
		return commentToAdmin;
	}

	public void setCommentToAdmin(String commentToAdmin) {
		this.commentToAdmin = commentToAdmin;
	}

	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMinSuccessMark() {
		return minSuccessMark;
	}

	public void setMinSuccessMark(int minSuccessMark) {
		this.minSuccessMark = minSuccessMark;
	}

	public String getResponseForUser() {
		return responseForUser;
	}

	public void setResponseForUser(String responseForUser) {
		this.responseForUser = responseForUser;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
