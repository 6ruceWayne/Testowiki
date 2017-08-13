package ua.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.java.models.Question;
import ua.java.models.Test;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findAllById(long id);

	@Query("SELECT q FROM Question q WHERE q.qTest = ?1")
	List<Question> findByTest(@Param("qTest") Test qTest);

	@Query("SELECT qTest FROM Question q WHERE q.id = ?1")
	Test findqTest(@Param("id") long id);
}
