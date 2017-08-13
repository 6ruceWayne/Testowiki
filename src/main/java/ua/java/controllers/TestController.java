package ua.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.java.models.Test;
import ua.java.models.TestStatus;
import ua.java.services.ResultInterfaceService;
import ua.java.services.SectionInterfaceService;
import ua.java.services.TestInterfaceService;

@Controller
@RequestMapping(value = "/tests")
public class TestController {

	private TestInterfaceService testService;
	@Autowired
	private ResultInterfaceService resService;
	@Autowired
	private SectionInterfaceService sectionService;

	@Autowired(required = true)
	@Qualifier(value = "testService")
	public void setTestService(TestInterfaceService testService) {
		this.testService = testService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listTests(Model model) {
		/*
		 * String welcome = messageSource.getMessage("label.registration", new
		 * Object[]{"John Doe"}, locale); model.addAttribute("message", welcome);
		 */
		System.out.println("Hello list tests!");
		model.addAttribute("test", new Test());
		model.addAttribute("listTests", this.testService.listTests());
		return "proposals";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTest(Test t) {
		if (t.getId() == 0) {
			this.testService.addTest(t);
		} else {
			// existing person, call update
			this.testService.updateTest(t);
		}
		return "redirect:/tests/list";
	}

	@RequestMapping(value = "/remove/{id}")
	public String removeTest(@PathVariable("id") long id) {
		this.testService.removeTest(id);
		return "redirect:/tests/list";
	}

	@RequestMapping("/review/{id}")
	public String editTest(@PathVariable("id") long id, Model model) {
		Test test = this.testService.getFullTestById(id);
		model.addAttribute("candidateTest", test);
		/*
		 * List<Long> list = new ArrayList<Long>(); list.add((long) 1); list.add((long)
		 * 2); list.add((long) 3);
		 */

		model.addAttribute("listSections", sectionService.getListSectionsLikeString());
		return "review";
	}

	@RequestMapping("/review/{id}/{section}")
	public void setSection(@PathVariable("id") long id, @PathVariable("section") String section, Model model) {
		Test test = this.testService.getTestById(id);
		test.setSectionEntity(sectionService.findByName(section));
		testService.addTest(test);
	}

	@RequestMapping(value = "/review/savechanges", method = RequestMethod.POST)
	public String saveChanges(@ModelAttribute("candidateTest") Test candidateTest, Model model) {
		testService.updateTest(candidateTest);
		model.addAttribute("candidateTest", candidateTest);
		model.addAttribute("candidateQuestions", candidateTest.getQuestions());
		// model.addAttribute("listSetions", arg1)
		return "redirect:/tests/list";
	}

	/*
	 * @ModelAttribute("candidateTest")
	 * 
	 * @RequestMapping(value = "/review/saveChanges", method = RequestMethod.POST)
	 * public String review(@PathVariable("candidateTest") Test candidateTest, Model
	 * model) { testService.addTest(candidateTest); return "redirect:/tests/list"; }
	 */

	@RequestMapping(value = "/choise/{id}/{status}", method = RequestMethod.GET)
	public String choise(@PathVariable("id") long id, @PathVariable("status") String status, Model model) {
		testService.setStatus(id, TestStatus.Developing.getStatus(status));
		return "redirect:/tests/list";
	}


}
