package ua.java.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.models.Test;
import ua.java.repository.QuestionRepository;

@Service
public class QuestionService implements QuestionInterfaceService {

	@Autowired
	private QuestionRepository questionRep;

	@Autowired
	private AnswerInterfaceService answerSer;

	@Override
	public void addQuestion(Question q) {
		// TODO Auto-generated method stub
		this.questionRep.save(q);
	}

	@Override
	public void updateQuestion(Question q) {
		// TODO Auto-generated method stub
		this.questionRep.save(q);
	}

	@Override
	public List<Question> listQuestions() {
		// TODO Auto-generated method stub
		return this.questionRep.findAll();
	}

	@Override
	public Question getQuestionById(long id) {
		// TODO Auto-generated method stub
		Question question = questionRep.findOne(id);
		return question;
	}

	@Override
	public void removeQuestion(long id) {
		// TODO Auto-generated method stub
		this.questionRep.delete(id);
	}

	@Override
	public List<Question> getListById(long id) {
		// TODO Auto-generated method stub
		return questionRep.findAllById(id);
	}

	@Override
	public List<Question> getListByTest(Test id) {
		return questionRep.findByTest(id);
	}

	@Override
	public Map<Long, Boolean> getAnswerMap(Question questionId) {
		// TODO Auto-generated method stub
		List<Answer> list = answerSer.getListByQuestion(questionId);
		Map<Long, Boolean> map = new HashMap<Long, Boolean>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).getId(), Boolean.FALSE);
			System.out.println(list.get(i).getId());
			System.out.println(map.get(list.get(i).getId()));
		}

		return map;
	}

	@Override
	public List<Answer> getAnswerList(Question question) {
		// TODO Auto-generated method stub
		return answerSer.getListByQuestion(question);
	}

	@Override
	public Test findqTest(Question question) {
		// TODO Auto-generated method stub
		return questionRep.findqTest(question.getId());
	}

}
