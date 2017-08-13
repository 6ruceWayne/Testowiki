package ua.java.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.java.models.Question;
import ua.java.models.Result;
import ua.java.models.ResultStatus;
import ua.java.models.Test;
import ua.java.models.User;
import ua.java.models.UserAnswer;
import ua.java.repository.ResultRepository;

@Service
@EnableScheduling
public class ResultService implements ResultInterfaceService {
	@Autowired
	private ResultRepository resRep;

	@Autowired
	private ResultCalcInterfaceService resCalc;

	@Autowired
	private UserService userSer;

	@Override
	public List<Result> findAllByTestId(Test qTest) {
		// TODO Auto-generated method stub
		return resRep.findAllByTestId(qTest);
	}

	@Override
	public List<Result> findAllByUserId(User user) {
		// TODO Auto-generated method stub
		return resRep.findAllByUserId(user);
	}

	@Override
	@Transactional
	public Date addResult(Test test, User user) {
		Result result = resRep.findAllByTestIdWhereStatusPassing(test, user);
		if (result != null) {
			Timestamp timeStamp = Timestamp
					.valueOf(result.getCreatedOn().toLocalDateTime().plusMinutes(test.getTime()));
			Date date = new Date(timeStamp.getTime());
			return date;
		} else {
			result = new Result();
			result.setrTest(test);
			result.setrUser(user);
			result.setStatus(ResultStatus.passing);
			Timestamp endOfPassing = new Timestamp(System.currentTimeMillis());
			endOfPassing = Timestamp.valueOf(endOfPassing.toLocalDateTime().plusMinutes(test.getTime()));
			result.setFinalDateTime(endOfPassing);
			resRep.save(result);
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			timeStamp = Timestamp.valueOf(timeStamp.toLocalDateTime().plusMinutes(test.getTime()));
			Date date = new Date(timeStamp.getTime());
			return date;
		}
	}

	@Override
	public Result findBycreatedOn(Timestamp createdOn) {
		// TODO Auto-generated method stub
		return resRep.findBycreatedOn(createdOn);
	}

	@Override
	public void inizialiseResult(Test test, User user) {
		// TODO Auto-generated method stub
		List<UserAnswer> userAnswers = new ArrayList<UserAnswer>();
		List<Question> list = test.getQuestions();
		Result result = new Result();
		for (int i = 0; i < list.size(); i++) {
			UserAnswer ua = new UserAnswer();
			ua.setUaResult(result);
			ua.setCorrect(false);
			ua.setUaQuestion(list.get(i));
			userAnswers.add(ua);
		}
		result.setUserAnswers(userAnswers);
		result.setrTest(test);
		result.setrUser(user);
		result.setStatus(ResultStatus.passing);
		Timestamp endOfPassing = new Timestamp(System.currentTimeMillis());
		endOfPassing = Timestamp.valueOf(endOfPassing.toLocalDateTime().plusMinutes(test.getTime()));
		result.setFinalDateTime(endOfPassing);
		resRep.save(result);
	}

	@Override
	public List<Result> findAllByUserIdWhereStatusDone(User user) {
		// TODO Auto-generated method stub
		return resRep.findAllByUserIdWhereStatusDone(user);
	}

	@Scheduled(fixedRate = 60000)
	public void changeStatus() {
		List<Result> list = resRep.findAllWhereStatusPassing();
		if (list != null) {
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			for (int i = 0; i < list.size(); i++) {
				Result result = list.get(i);
				if (result.getFinalDateTime().before(currentTime)) {
					resCalc.finishTest(resRep.findrUser(result.getId()), resRep.findrTest(result.getId()));
					resRep.save(result);
				}
			}
		}
	}

	@Override
	public Result getResultById(long id) {
		// TODO Auto-generated method stub
		return resRep.findOne(id);
	}

}
