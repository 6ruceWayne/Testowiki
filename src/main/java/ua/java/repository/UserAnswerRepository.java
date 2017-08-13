package ua.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.java.models.Question;
import ua.java.models.Result;
import ua.java.models.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

	@Query("SELECT u FROM UserAnswer u WHERE u.uaQuestion = ?1 AND u.uaResult = ?2")
	UserAnswer findAllByResultIdAndQuestionId(@Param("uaQuestion") Question uaQuestion,
			@Param("uaResult") Result uaResult);

	List<UserAnswer> findAllByuaResult(Result result);
}
