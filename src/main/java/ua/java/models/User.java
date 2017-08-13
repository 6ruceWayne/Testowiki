package ua.java.models;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	private String username;
	private String password;
	private String passwordConfirm;
	@Column(length = 32, columnDefinition = "varchar(32) default 'English'")
	@Enumerated(EnumType.STRING)
	private LocaleLanguage locale;
	private boolean isVerificate;
	@OneToOne(mappedBy = "vUser", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private VerificateGenerator verificateGenerator;
	@OneToOne(mappedBy = "arUser", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Article article;
	@OneToMany(mappedBy = "aUser", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<UserAnswer> userAnswer;
	@OneToMany(mappedBy = "tAuthor", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Test> listTests = new ArrayList<Test>();
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@OneToMany(mappedBy = "rUser", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private List<Result> result = new ArrayList<Result>();
	@CreationTimestamp
	private Timestamp dateRegistration;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public LocaleLanguage getLocale() {
		return locale;
	}

	public void setLocale(LocaleLanguage locale) {
		this.locale = locale;
	}

	public VerificateGenerator getVerificateGenerator() {
		return verificateGenerator;
	}

	public void setVerificateGenerator(VerificateGenerator verificateGenerator) {
		this.verificateGenerator = verificateGenerator;
	}

	public boolean getIsVerificate() {
		return isVerificate;
	}

	public void setIsVerificate(boolean isVerificate) {
		this.isVerificate = isVerificate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserAnswer> getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(List<UserAnswer> userAnswer) {
		this.userAnswer = userAnswer;
	}

	public List<Test> getListTests() {
		return listTests;
	}

	public void setListTests(List<Test> listTests) {
		this.listTests = listTests;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateRegistration() {
		return dateRegistration;
	}

	public void setDateRegistration(Timestamp dateRegistration) {
		this.dateRegistration = dateRegistration;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return username;
	}
}
