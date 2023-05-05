package ua.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// все методы данного контролера отслеживают запрос к адресу
// http://localhost:8080/springMVC/step/
@RequestMapping("/step")
public class Task04Controller {

	// Метод вызывается при отправке запроса на вдресс
	// http://localhost:8080/springMVC/step/test1
	@RequestMapping(value = "/test1")
	public String requestMapped1() {
		// переиспользование представления из примера 1
		return "task01";
	}

	@RequestMapping(value = "/test2")
	public String requestMapped2() {
		// переиспользование представления из примера 2
		return "task02";
	}

	@RequestMapping(value = "/task04")
	public String simple1() {
		return "task04";
	}
}
// FIXME: исправь контроллер, чтобы при переходе по ссылкам выводилось время и Hello Alex 
