package ua.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Task07Controller {

	@GetMapping("/task07")
	public String get(@RequestParam("page") Integer param, Model model) {
		// тут может быть ваша логика по считыванию значений из базы
		if (param == null){
			param = 1;
		}
		model.addAttribute("pageCount", param);
		return "task07";
	}
}

