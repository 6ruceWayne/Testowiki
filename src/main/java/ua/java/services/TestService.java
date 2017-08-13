package ua.java.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.models.Section;
import ua.java.models.Test;
import ua.java.models.TestStatus;
import ua.java.models.User;
import ua.java.repository.SectionRepository;
import ua.java.repository.TestRepository;

@Service
public class TestService implements TestInterfaceService {

	@Autowired
	private TestRepository testRep;

	@Autowired
	private QuestionInterfaceService questionServ;

	@Autowired
	private AnswerInterfaceService answerServ;

	@Autowired
	private SectionRepository secRep;

	@Override
	public Test addTest(Test t) {
		// TODO Auto-generated method stub
		this.testRep.save(t);
		return t;
	}

	@Override
	@Transactional
	public void updateTest(Test t) {
		// TODO Auto-generated method stub
		/*
		 * Test test = testRep.findOne(t.getId()); long id = test.getId(); Test newTest
		 * = t; this.testRep.save(t);
		 */
		testRep.save(t);
	}

	@Override
	public List<Test> listTests() {
		// TODO Auto-generated method stub
		return this.testRep.findAll();
	}

	@Override
	public Test getTestById(Long id) {
		return this.testRep.findOne(id);
	}

	@Override
	public void removeTest(long id) {
		// TODO Auto-generated method stub
		this.testRep.delete(id);
	}

	@Override
	public void removeQuestion(long id) {
		// TODO Auto-generated method stub
		// answerServ.removeAnswersWhereQuestion(questionServ.getQuestionById(id));
		questionServ.removeQuestion(id);
	}

	@Override
	public void removeAnswer(long id) {
		// TODO Auto-generated method stub
		answerServ.removeAnswer(id);
	}

	public Test addQuestion(Question question, long testId) {
		// TODO Auto-generated method stub
		question.setqTest(testRep.getTestById(testId));
		questionServ.addQuestion(question);
		return getFullTestById(testId);
	}

	@Override
	@Transactional
	public List<Question> getListQuestionsById(long id) {
		Test test = testRep.getOne(id);
		List<Question> list = test.getQuestions();
		return list;
	}

	@Override
	public Test getFullTestById(long id) {
		// TODO Auto-generated method stub
		Test test = this.getTestById(id);
		List<Question> list = questionServ.getListByTest(test);
		if (list != null) {
			for (Question q : list) {
				List<Answer> listAnsw = answerServ.getListByQuestion(q);
				q.setAnswers(listAnsw);
			}
			if (list.size() > 0) {
				test.setQuestions(list);
			}
		}
		return test;
	}

	@Override
	public List<Test> findAllByAuthorId(User user) {
		return testRep.getListTestsBytAuthor(user);
	}

	@Override
	public void setStatus(long id, TestStatus status) {
		// TODO Auto-generated method stub
		Test test = testRep.findOne(id);
		test.setStatus(status);
		testRep.save(test);
	}

	@Override
	public Test addAnswer(Answer s, long questionId) {
		// TODO Auto-generated method stub
		Question question = questionServ.getQuestionById(questionId);
		List<Answer> list = new ArrayList<Answer>();
		list.add(s);
		question.setAnswers(list);
		long testId = question.getqTest().getId();
		questionServ.addQuestion(question);
		return getFullTestById(testId);
	}

	@Override
	public Test getTestByQuestion(Question question) {
		// TODO Auto-generated method stub

		return testRep.findOne(questionServ.findqTest(question).getId());
	}

	@Override
	public LinkedHashMap<Section, List<Test>> getMapSections() {
		// TODO Auto-generated method stub
		LinkedHashMap<Section, List<Test>> map = new LinkedHashMap<Section, List<Test>>();
		List<Section> listSec = secRep.findAll();
		for (int i = 0; i < listSec.size(); i++) {
			map.put(listSec.get(i), testRep.findBySectionEntity(listSec.get(i)));
		}
		return map;
	}

	@Override
	public List<Test> getTop3Visited() {
		// TODO Auto-generated method stub
		return testRep.findFirst3ByOrderByCountDesc();
	}

}
