package ua.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Task09Controller {

	/**
	 * Аннотация @RequestParam не является строго обязательной. Ее удобно использовать для
	 * связывания параметров запроса с параметрами метода, когда их имена
	 * отличаются. В соответствии с соглашениями все неаннотированные параметры
	 * метода обработчика будут связаны с одноименными параметрами запроса.
	 */	
	@GetMapping("/task09")
	public String get(@RequestParam("page") Integer param, Model model) {
		// тут может быть ваша логика по считыванию значений из базы
		if (param == null){
			param = 1;
		}
		if (param > 5) param = 5;
		if (param <= 0) param = 1;
		model.addAttribute("pageCount", param);
		return "task09";
	}
	
	@GetMapping("/task09/next")
	public String nextPage(@RequestParam("page") Integer param, Model model) {
		if (param > 5) param = 5;
		model.addAttribute("pageCount", param);
		return "redirect: /task09";
	}

	@GetMapping("/task09/previous")
	public String previousPage(@RequestParam("page") Integer param, Model model) {
		if (param <= 0) param = 1;
		model.addAttribute("pageCount", param);
		return "redirect: /task09";
	}
}