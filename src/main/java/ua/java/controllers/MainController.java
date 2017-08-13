package ua.java.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.java.models.LocaleLanguage;
import ua.java.models.User;
import ua.java.services.ArticleInterfaceService;
import ua.java.services.ResultInterfaceService;
import ua.java.services.SectionInterfaceService;
import ua.java.services.SecurityService;
import ua.java.services.TestInterfaceService;
import ua.java.services.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private ResultInterfaceService resService;
	@Autowired
	private SecurityService securityService;
	private TestInterfaceService testService;
	@Autowired
	private SectionInterfaceService sectionService;
	

	@Autowired(required = true)
	@Qualifier(value = "testService")
	public void setTestService(TestInterfaceService testService) {
		this.testService = testService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest req) {
		System.out.println("Hello user!");

		System.out.println(LocaleContextHolder.getLocale());
		model.addAttribute("listTopVisited", testService.getTop3Visited());
		model.addAttribute("mapTests", testService.getMapSections());
		return "index";
	}

	@RequestMapping(value = "/rules", method = RequestMethod.GET)
	public String rules(Model model) {
		return "rules";
	}

	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public String faq(Model model) {
		return "FAQ";
	}



	@RequestMapping(value = "/locale/{locale}", method = RequestMethod.POST)
	public void locale(@PathVariable("locale") String locale, Model model) {
		User user = userService.findByUsername(securityService.getName());
		user.setLocale(LocaleLanguage.Russian.getLocale(locale));
		userService.save(user);
		System.out.println("Save Locale user");
	}


}
