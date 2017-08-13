package ua.java.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import ua.java.models.Question;
import ua.java.models.Result;
import ua.java.models.Test;
import ua.java.models.User;

public interface ResultCalcInterfaceService {

	public void setAnswer(Test test, long questionId, List answers);

	public void setAnswer(User user, Question question, Map<Long, Boolean> map);

	public Result finishTest(User user, Test test);

	public Result finishTest(User user, Test test, Timestamp finishByUserDateTime);
}
