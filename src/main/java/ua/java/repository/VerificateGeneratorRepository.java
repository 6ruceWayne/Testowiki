package ua.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.java.models.VerificateGenerator;

public interface VerificateGeneratorRepository extends JpaRepository<VerificateGenerator, Long> {
	VerificateGenerator findByVerife(String verife);

}
