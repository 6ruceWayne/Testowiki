package ua.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.java.models.Article;
import ua.java.models.Test;
import ua.java.models.User;
import ua.java.repository.ArticleRepository;

@Service
public class ArticleService implements ArticleInterfaceService {

	@Autowired
	private ArticleRepository articleRep;

	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return articleRep.findAll();
	}

	@Override
	public void addArticle(String name, String text, User user) {
		// TODO Auto-generated method stub
		Article article = new Article();
		article.setName(name);
		article.setArUser(user);
		article.setContent(text);
		articleRep.save(article);
	}

	@Override
	public void addArticle(String name, String text, User user, Test test) {
		// TODO Auto-generated method stub
		Article article = new Article();
		article.setName(name);
		article.setArUser(user);
		article.setContent(text);
		article.setArTest(test);
		articleRep.save(article);
	}

	@Override
	public Article getAtricleByTest(Test test) {
		// TODO Auto-generated method stub
		return articleRep.findByArTest(test);
	}

	@Override
	public List<Article> getArticlesByUser(User user) {
		// TODO Auto-generated method stub
		return articleRep.findByArUser(user);
	}

	@Override
	public void changeArticle(String name, String text, long id) {
		// TODO Auto-generated method stub
		Article article = articleRep.findOne(id);
		article.setName(name);
		article.setContent(text);
		articleRep.save(article);
	}

	@Override
	public Article getArticleByName(String name) {
		// TODO Auto-generated method stub
		return articleRep.findByName(name);
	}

	@Override
	public Article getArticleById(long id) {
		// TODO Auto-generated method stub
		return articleRep.findOne(id);
	}

}
