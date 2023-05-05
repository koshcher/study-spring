package ua.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Task02Controller {
	/**
	 * Использование модели и представления
	 */
	@RequestMapping(value = "/task02")
	public ModelAndView getModelAndView() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("name", "Mike");
		modelAndView.setViewName("task02");
		return modelAndView;
	}
	
	/**
	 * Использования модели 
	 */
	@RequestMapping(value = "/task03")
	public String getModel(Model model) {
		model.addAttribute("name", "John");
		model.addAttribute("path", "/task03");
		return "task03";
	}
	
	/**
	 * 
	 * Не используется view ответ возврашается как есть
	 */
	@RequestMapping(value = "/task2")
	@ResponseBody // без заголовков html или шаблона
	public String getBody() {
		return "Hello Fill";
	}
}