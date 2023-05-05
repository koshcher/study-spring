package ua.step.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.model.Country;

@Controller
public class Task06Controller {
	private List<Country> countries = new ArrayList<>();

	@PostConstruct
	public void init() {
		countries.add(new Country("Украина", 380));
		countries.add(new Country("Россия", 7));
		countries.add(new Country("Грузия", 995));
		countries.add(new Country("Молдова", 373));
	}
	
	/**
	 * Значение атрибута method устанавливает какой тип запроса обрабатывает данный
	 * метод, в данном случае GET
	 */
	@GetMapping("/task06")
	public String requestGet(Model model) {
		// добавление атрибута с именем country используется в представлении для связывания значение формы и объекта
		model.addAttribute("country", new Country());
		
		// добавление атрибута с именем countries используется в представлении для отображения списка стран
		model.addAttribute("countries", countries);
		return "task06";
	}

	/*
	 * Аннотация @PostMapping является короткой формой @RequestMapping для POST запросов
	 */
	@PostMapping("/task06")
	public String requesPost(Model model, @ModelAttribute Country country) {
		countries.add(country);
		model.addAttribute("countries", countries);
		return "task06";
	}
}