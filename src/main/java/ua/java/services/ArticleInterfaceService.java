package ua.java.services;

import java.util.List;

import ua.java.models.Article;
import ua.java.models.Test;
import ua.java.models.User;

public interface ArticleInterfaceService {
	public void addArticle(String name, String text, User user);

	public void addArticle(String name, String text, User user, Test test);

	public List<Article> getAllArticles();

	public Article getAtricleByTest(Test test);

	public List<Article> getArticlesByUser(User user);

	public void changeArticle(String name, String text, long id);

	public Article getArticleByName(String name);

	public Article getArticleById(long id);
}
