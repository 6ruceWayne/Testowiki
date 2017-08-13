package ua.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.java.models.Question;
import ua.java.models.Section;
import ua.java.models.Test;
import ua.java.models.User;

public interface TestRepository extends JpaRepository<Test, Long> {
	Test findTestBytAuthor(User user);

	List<Test> findAll();

	public Test getTestById(long id);

	public List<Question> getListQuestionsById(long id);

	@Query("SELECT q FROM Question q WHERE q.qTest = ?1")
	List<Question> findQuestionsByTestId(@Param("qTest") Test qTest);

	List<Test> getListTestsBytAuthor(User user);

	List<Test> findBySectionEntity(Section section);

	public List<Test> findFirst3ByOrderByCountDesc();

}
