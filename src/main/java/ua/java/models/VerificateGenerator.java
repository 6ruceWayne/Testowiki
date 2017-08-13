package ua.java.models;

import java.sql.Timestamp;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "verificate")
public class VerificateGenerator {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String verife;
	@Column(length = 32, columnDefinition = "varchar(32) default 'newUser'")
	@Enumerated(EnumType.STRING)
	private AppointmentVerification appointmentVerification;
	@OneToOne
	@JoinColumn(name = "userId")
	private User vUser;
	@CreationTimestamp
	private Timestamp createdOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public User getvUser() {
		return vUser;
	}

	public void setvUser(User vUser) {
		this.vUser = vUser;
	}

	public AppointmentVerification getAppointmentVerification() {
		return appointmentVerification;
	}

	public void setAppointmentVerification(AppointmentVerification appointmentVerification) {
		this.appointmentVerification = appointmentVerification;
	}

	public String getVerife() {
		return verife;
	}

	public void setVerife(String verife) {
		this.verife = verife;
	}

	public void generateString(int length) {
		String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
		Random rnd = new Random();
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rnd.nextInt(characters.length()));
		}
		this.verife = new String(text);
	}

}
