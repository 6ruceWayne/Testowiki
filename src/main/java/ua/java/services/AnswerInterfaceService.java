package ua.java.services;

import java.util.List;

import ua.java.models.Answer;
import ua.java.models.Question;

public interface AnswerInterfaceService {
	public void addAnswer(Answer a);

	public void updateQuestion(Answer a);

	public List<Answer> listAnswers();

	public Answer getAnswerById(long id);

	public void removeAnswer(long id);

	public List<Answer> getListById(long id);

	public List<Answer> getListByQuestion(Question id);

	public List<Answer> getListTrueByQuestion(Question id);

	public Answer recoding(Answer answer);

	public void removeAnswersWhereQuestion(Question question);
}
