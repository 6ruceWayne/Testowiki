package ua.java.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.java.services.ArticleInterfaceService;

@Controller
public class ArticleController {
	@Autowired
	private ArticleInterfaceService articleService;
	

	@RequestMapping(value = "/articleCreate", method = RequestMethod.GET)
	public String articleForm(Model model) {
		return "articleCreate";
	}

	@RequestMapping(value = "/articleCreate", method = RequestMethod.POST)
	public String articleCreate(HttpServletRequest req, Model model) {
		System.out.println("Залетело");
		/*
		 * Enumeration attr = req.getParameterNames(); while (attr.hasMoreElements())
		 * System.out.println(attr.nextElement());
		 */
		System.out.println(req.getParameter("articleName"));
		System.out.println(req.getParameter("articleContent"));
		System.out.println("Залетело???");
		return "articleCreate";
	}

	@RequestMapping(value = "/articleList", method = RequestMethod.POST)
	public String articleList(Model model) {
		model.addAttribute("articleList", articleService.getAllArticles());
		return "articlesList";
	}

	@RequestMapping(value = "/readArticle/{id}", method = RequestMethod.POST)
	public String readArticle(@PathVariable("id") long id, Model model) {
		model.addAttribute("article", articleService.getArticleById(id));
		return "article";
	}

}
