package ua.step.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.step.model.User;

@Controller
@RequestMapping("/users")
public class Task10Controller {
	@RequestMapping(method = RequestMethod.GET)
	public String getForm(Model model) {
		model.addAttribute("user", new User());
		return "task10";
	}

	/*
	 * Аннотация @PostMapping является более короткой формой @RequestMapping для POST запросов
	 */
	@PostMapping
	public String addUser(@Valid User user, BindingResult bindingResult) {
		// Проверка ошибок в обрабатываемой форме
		if (bindingResult.hasErrors()) {
			return "task10";
		}
		return "doneUser";
	}
}