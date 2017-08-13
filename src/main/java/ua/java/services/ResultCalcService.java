package ua.java.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Answer;
import ua.java.models.Question;
import ua.java.models.Result;
import ua.java.models.ResultStatus;
import ua.java.models.Test;
import ua.java.models.User;
import ua.java.models.UserAnswer;
import ua.java.repository.QuestionRepository;
import ua.java.repository.ResultRepository;
import ua.java.repository.TestRepository;
import ua.java.repository.UserAnswerRepository;

@Service
public class ResultCalcService implements ResultCalcInterfaceService {

	@Autowired
	private ResultRepository resRep;

	@Autowired
	private TestRepository testRep;

	@Autowired
	private QuestionRepository questionRep;

	@Autowired
	private UserAnswerRepository userRep;

	@Autowired
	private AnswerInterfaceService answerService;

	@Autowired
	private ResultInterfaceService resultService;

	@Autowired
	private UserAnswerRepository userAnswerRep;

	@Override
	public void setAnswer(Test test, long questionId, List answers) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setrTest(test);
		// resSer.addResult(result);
	}

	public boolean isCorrect(Question question, Map<Long, Boolean> map) {
		List<Answer> list = answerService.getListTrueByQuestion(question);
		if (map.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Answer answer = list.get(i);
				System.out.println(map);
				Boolean bool = map.get(new Long(answer.getId()));
				if (bool == null) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setAnswer(User user, Question question, Map<Long, Boolean> map) {
		// TODO Auto-generated method stub
		Test test = questionRep.findqTest(question.getId());
		// Test test = testRep.findOne(num);
		// Test test = testRep.findOne(num);
		Result result = resRep.findAllByTestIdWhereStatusPassing(test, user);
		if (result != null) {
			UserAnswer userAnswerRes = userAnswerRep.findAllByResultIdAndQuestionId(question, result);
			boolean questionRes = isCorrect(question, map);
			if (userAnswerRes != null) {
				if (questionRes) {
					userAnswerRes.setCorrect(true);
					userAnswerRes.setUaQuestion(question);
					userAnswerRes.setUaResult(result);
					userAnswerRes.setaUser(user);
					userRep.save(userAnswerRes);
				} else {
					userAnswerRes.setCorrect(false);
					userAnswerRes.setUaQuestion(question);
					userAnswerRes.setUaResult(result);
					userAnswerRes.setaUser(user);
					userRep.save(userAnswerRes);
				}
			} else {
				if (questionRes) {
					UserAnswer userAnswer = new UserAnswer();
					userAnswer.setCorrect(true);
					userAnswer.setUaQuestion(question);
					userAnswer.setUaResult(result);
					userAnswer.setaUser(user);
					userRep.save(userAnswer);
				} else {
					UserAnswer userAnswer = new UserAnswer();
					userAnswer.setCorrect(false);
					userAnswer.setUaQuestion(question);
					userAnswer.setUaResult(result);
					userAnswer.setaUser(user);
					userRep.save(userAnswer);
				}
			}
		} else {
			resultService.inizialiseResult(test, user);
			setAnswer(user, question, map);
		}
	}

	@Override
	public Result finishTest(User user, Test test, Timestamp finishByUserDateTime) {
		// TODO Auto-generated method stub
		Result result = resRep.findAllByTestIdWhereStatusPassing(test, user);
		List<UserAnswer> list = userRep.findAllByuaResult(result);
		int correctAnswers = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isCorrect()) {
				correctAnswers++;
			}
		}
		result.setCorrectAnswer(correctAnswers);
		result.setFinishByUserDateTime(finishByUserDateTime);
		int minMark = 0;
		List<Question> listQ = questionRep.findByTest(test);
		if (test.getMinSuccessMark() > 0) {
			minMark = test.getMinSuccessMark();
		} else {
			minMark = listQ.size() / 2;
		}
		int mark = 0;
		if (correctAnswers == 0) {
			mark = 0;
		} else {
			mark = (listQ.size() * 100) / correctAnswers;
		}
		result.setMark(mark);
		result.setMinMark(minMark);
		if (correctAnswers >= minMark) {
			result.setPassed(true);
		} else {
			result.setPassed(false);
		}
		result.setQuestions(listQ.size());
		result.setStatus(ResultStatus.done);
		resRep.save(result);
		return result;
	}

	@Override
	public Result finishTest(User user, Test test) {
		// TODO Auto-generated method stub
		Result result = resRep.findAllByTestIdWhereStatusPassing(test, user);
		List<UserAnswer> list = userRep.findAllByuaResult(result);
		int correctAnswers = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isCorrect()) {
				correctAnswers++;
			}
		}
		result.setCorrectAnswer(correctAnswers);
		int minMark = 0;
		List<Question> listQ = questionRep.findByTest(test);
		if (test.getMinSuccessMark() > 0) {
			minMark = test.getMinSuccessMark();
		} else {
			minMark = listQ.size() / 2;
		}
		int mark = 0;
		if (correctAnswers == 0) {
			mark = 0;
		} else {
			mark = (listQ.size() * 100) / correctAnswers;
		}
		result.setMark(mark);
		result.setMinMark(minMark);
		if (correctAnswers >= minMark) {
			result.setPassed(true);
		} else {
			result.setPassed(false);
		}
		result.setQuestions(listQ.size());
		result.setStatus(ResultStatus.done);
		resRep.save(result);
		return result;
	}

}
