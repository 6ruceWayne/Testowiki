package ua.java.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.repository.AnswerRepository;

@Service
public class AnswerService implements AnswerInterfaceService {

	@Autowired
	private AnswerRepository answerRep;

	@Override
	public void addAnswer(Answer a) {
		// TODO Auto-generated method stub
		this.answerRep.save(a);
	}

	@Override
	public void updateQuestion(Answer a) {
		// TODO Auto-generated method stub
		this.answerRep.save(a);
	}

	@Override
	public List<Answer> listAnswers() {
		// TODO Auto-generated method stub
		return this.answerRep.findAll();
	}

	@Override
	public Answer getAnswerById(long id) {
		// TODO Auto-generated method stub
		return this.answerRep.getOne(id);
	}

	@Override
	public void removeAnswer(long id) {
		// TODO Auto-generated method stub
		this.answerRep.delete(id);
	}

	@Override
	public List<Answer> getListById(long id) {
		// TODO Auto-generated method stub
		return this.answerRep.findAllById(id);
	}

	@Override
	public List<Answer> getListByQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.answerRep.findByQuestion(question);
	}

	@Override
	public Answer recoding(Answer answer) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void removeAnswersWhereQuestion(Question question) {
		// TODO Auto-generated method stub
		answerRep.removeAnswersWhereQuestionId(question);
	}

	@Override
	public List<Answer> getListTrueByQuestion(Question question) {
		// TODO Auto-generated method stub
		List<Answer> list1 = answerRep.findByQuestion(question);
		List<Answer> list2 = new ArrayList<Answer>();
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i).isCorrect()) {
				list2.add(list1.get(i));
			}
		}
		return list2;
	}

}
