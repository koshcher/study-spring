package ua.step.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Контроллер Hello World Аннотация @Controller указывает, что данный класс
 * играет в Spring роль контроллера. Потому нет необходимости наследования
 * какого-либо базового класса контроллера или использования Servlet API.
 */
@Controller // Объявить как контроллер
public class Task01Controller {
	/**
	 * Метод выполнится при запросе на адрес http://localhost:8080/module8/task01
	 */
	@RequestMapping("/task01")
	public String getHome(Model model, Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		// добавление атрибута в модель
		model.addAttribute("serverTime", formattedDate); //
		return "task01"; // возврашает имя представлени для отобажения. Соответствует странице task01.jsp
	}
}
