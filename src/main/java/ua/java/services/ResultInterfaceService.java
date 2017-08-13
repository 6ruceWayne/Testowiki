package ua.java.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import ua.java.models.Result;
import ua.java.models.Test;
import ua.java.models.User;

public interface ResultInterfaceService {
	public List<Result> findAllByTestId(Test qTest);

	public List<Result> findAllByUserId(User user);

	public List<Result> findAllByUserIdWhereStatusDone(User user);

	public Date addResult(Test test, User user);

	public Result findBycreatedOn(Timestamp createdOn);

	public void inizialiseResult(Test test, User user);

	public void changeStatus();

	public Result getResultById(long id);

}
