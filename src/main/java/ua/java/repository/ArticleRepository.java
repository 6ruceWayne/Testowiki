package ua.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.java.models.Answer;
import ua.java.models.Article;
import ua.java.models.Question;
import ua.java.models.Test;
import ua.java.models.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	Article findByArTest(Test test);

	List<Article> findByArUser(User user);

	Article findByName(String name);
}
