package ua.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.java.models.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
	Section findByName(String name);
	
}
