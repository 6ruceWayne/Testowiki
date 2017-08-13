package ua.java.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.models.Section;
import ua.java.models.Test;
import ua.java.models.TestStatus;
import ua.java.models.User;

public interface TestInterfaceService {
	public Test addTest(Test p);

	public void updateTest(Test p);

	public List<Test> listTests();

	public Test getTestById(Long id);

	public void removeTest(long id);

	public void removeQuestion(long id);

	public void removeAnswer(long id);

	public List<Question> getListQuestionsById(long id);

	public Test getFullTestById(long id);

	List<Test> findAllByAuthorId(User user);

	public Test addQuestion(Question q, long testId);

	public Test addAnswer(Answer s, long questionId);

	public void setStatus(long id, TestStatus status);

	public Test getTestByQuestion(Question question);

	public LinkedHashMap<Section, List<Test>> getMapSections();

	public List<Test> getTop3Visited();
}
