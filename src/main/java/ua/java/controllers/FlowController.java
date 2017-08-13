package ua.java.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.java.models.AnswerByUser;
import ua.java.models.Article;
import ua.java.models.Question;
import ua.java.models.Result;
import ua.java.models.Test;
import ua.java.models.UserAnswerForm;
import ua.java.services.ArticleInterfaceService;
import ua.java.services.QuestionInterfaceService;
import ua.java.services.ResultCalcInterfaceService;
import ua.java.services.ResultInterfaceService;
import ua.java.services.SecurityService;
import ua.java.services.TestInterfaceService;
import ua.java.services.UserService;

@Controller
public class FlowController {
	@Autowired
	private TestInterfaceService testService;
	@Autowired
	private ResultCalcInterfaceService resCalc;
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private QuestionInterfaceService questionService;
	@Autowired
	private ResultInterfaceService resService;
	@Autowired
	private ResultCalcInterfaceService resCalcService;
	@Autowired
	private ArticleInterfaceService articleService;

	@RequestMapping(value = "/previewTest/{id}")
	public String previewTest(@PathVariable("id") long id, Model model) {
		Test test = this.testService.getTestById(id);
		Article article = articleService.getAtricleByTest(test);
		model.addAttribute("ourTest", test);
		model.addAttribute("ourArticle", article);
		return "previewTest";
	}

	@RequestMapping(value = "/testPassing/{id}", method = RequestMethod.GET)
	public String testPassing(@PathVariable("id") long id, Model model) {
		Test test = this.testService.getFullTestById(id);
		model.addAttribute("ourTest", test);
		return "testPassing";
	}

	@RequestMapping(value = "/passing/{id}")

	public String startPassing(@PathVariable("id") long id, @RequestParam(required = false) Integer qId, Model model) {
		System.out.println("StartPassing Int = " + qId);
		if (securityService.getName() != null) {
			Test test = this.testService.getFullTestById(id);
			Date date = resService.addResult(test, userService.findByUsername(securityService.getName()));
			model.addAttribute("passingTest", test);
			int pageSize = test.getQuestions().size();
			List<Question> questions = test.getQuestions();
			model.addAttribute("questionSize", test.getQuestions().size());
			SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy HH:mm:ss", Locale.ENGLISH);
			String dateString = formatter.format(date);
			model.addAttribute("ourDate", dateString);
			model.addAttribute("Long", new Long(0));
			model.addAttribute("AnswerForm", new AnswerByUser());
			if (qId == null || qId < 1 || qId > pageSize) {
				qId = 1;
				model.addAttribute("questionId", questions.get(qId).getId());
				model.addAttribute("question", questions.get(qId));
				Map<Long, Boolean> listAnswers = questionService.getAnswerMap(questions.get(qId));
				UserAnswerForm userAnswerForm = new UserAnswerForm();
				userAnswerForm.setMap(listAnswers);

				System.out.println(userAnswerForm.getMap());
				model.addAttribute("userAnswerForm", userAnswerForm);
			} else if (qId <= pageSize) {
				model.addAttribute("questionId", questions.get(qId - 1).getId());
				model.addAttribute("question", questions.get(qId - 1));
				Map<Long, Boolean> listAnswers = questionService.getAnswerMap(questions.get(qId - 1));
				// System.out.println(listAnswers.get(new Long(48)));
				UserAnswerForm userAnswerForm = new UserAnswerForm();
				// System.out.println(userAnswerForm.getList().get(new Long(48)));
				userAnswerForm.setMap(listAnswers);
				// System.out.println(userAnswerForm.getList().get(new Long(48)));
				model.addAttribute("userAnswerForm", userAnswerForm);
			}

			return "testPassing";
		} else {
			return "redirect:/registration";
		}
	}

	@RequestMapping(value = "/setAnswer", method = RequestMethod.POST)
	public String setAnswer(@ModelAttribute("userAnswerForm") UserAnswerForm userAnswerForm, Model model,
			RedirectAttributes redirectAttributes) {
		Integer qId2 = userAnswerForm.getPage();

		System.out.println(qId2);
		redirectAttributes.addFlashAttribute("qId", qId2);
		Question question = questionService.getQuestionById(userAnswerForm.getQuestionid());
		// System.out.println(userAnswerForm.getList().get(1));
		Map<Long, Boolean> listAnswers2 = userAnswerForm.getMap();
		resCalc.setAnswer(userService.findByUsername(securityService.getName()), question, listAnswers2);
		Test test = question.getqTest();
		return "redirect:/passing/" + test.getId();
	}

	@RequestMapping(value = "/finishTest/{id}", method = RequestMethod.GET)
	public String finishTest(@PathVariable("id") long id, Model model) {
		System.out.println("Запрос получен!");
		Result result = resCalcService.finishTest(userService.findByUsername(securityService.getName()),
				testService.getFullTestById(id), new Timestamp(System.currentTimeMillis()));
		model.addAttribute("testResult", result);
		return "redirect:/resultView/" + result.getId();
	}

	@RequestMapping(value = "/resultView/{id}", method = RequestMethod.GET)
	public String resultView(@PathVariable("id") long id, Model model) {
		System.out.println("Запрос получен!");
		Result result = resService.getResultById(id);
		model.addAttribute("testResult", result);
		return "resultView";
	}

}
