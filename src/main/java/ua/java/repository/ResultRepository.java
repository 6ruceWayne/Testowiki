package ua.java.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.java.models.Result;
import ua.java.models.Test;
import ua.java.models.User;

public interface ResultRepository extends JpaRepository<Result, Long> {

	@Query("SELECT r FROM Result r WHERE r.rTest = ?1")
	List<Result> findAllByTestId(@Param("tResult") Test qTest);

	@Query("SELECT r FROM Result r WHERE r.rUser = ?1")
	List<Result> findAllByUserId(@Param("uResult") User user);

	Result findBycreatedOn(Timestamp createdOn);

	@Query("SELECT r FROM Result r WHERE r.rTest = ?1 AND r.status = 'passing' AND r.rUser = ?2")
	Result findAllByTestIdWhereStatusPassing(@Param("rTest") Test qTest, @Param("rUser") User rUser);

	@Query("SELECT r FROM Result r WHERE r.status = 'done' AND r.rUser = ?1")
	List<Result> findAllByUserIdWhereStatusDone(@Param("rUser") User rUser);

	@Query("SELECT r FROM Result r WHERE r.status = 'passing'")
	List<Result> findAllWhereStatusPassing();

	@Query("SELECT rTest FROM Result r WHERE r.id = ?1")
	Test findrTest(@Param("id") long id);

	@Query("SELECT rUser FROM Result r WHERE r.id = ?1")
	User findrUser(@Param("id") long id);

	public Result getResultById(long id);
}
