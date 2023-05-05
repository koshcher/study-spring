package ua.step.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.step.model.City;
import ua.step.model.Country;

@Controller
public class Task05Controller {
	private Map<Country, List<City>> countries = new HashMap<>();

	// аннотация @PostConstruct позволяет выполнить код после создания бина, но до его использования.
	@PostConstruct
	public void init() {
		List<City> ukrCities = new ArrayList<>();
		ukrCities.add(new City("Одеса", 48));
		ukrCities.add(new City("Киев", 44));
		ukrCities.add(new City("Полтава", 532));
		ukrCities.add(new City("Херсон", 552));
		ukrCities.add(new City("Харков", 57));
		countries.put(new Country("Украина", 380), ukrCities);

		ArrayList<City> rusCities = new ArrayList<>();
		rusCities.add(new City("Берлин", 30));
		rusCities.add(new City("Гамбург", 40));
		rusCities.add(new City("Бремен", 812));
		countries.put(new Country("Германия", 471), rusCities);

		ArrayList<City> georCities = new ArrayList<>();
		georCities.add(new City("Тбилиси", 12));
		georCities.add(new City("Батуми", 32));
		countries.put(new Country("Грузия", 995), georCities);
	}

	
	/*
	 * @RequestMapping(path = "/countries", method = RequestMethod.GET)
	 * Аннотация @GetMapping является более короткой формой @RequestMapping для GET запросов
	 */
	@GetMapping("/countries")
	public String getCountries(Model model, Integer countryCode) {

		model.addAttribute("countries", countries.keySet());

		if (countryCode != null) {
			Country selectCountru = null;
			for (Country country : countries.keySet()) {
				if (countryCode.equals(country.getCode())) {
					selectCountru = country;
				}
			}
			model.addAttribute("countryMap", countries);
			model.addAttribute("country", selectCountru);
		}
		return "task05";
	}

	@GetMapping("/countries/{countryCode}")
	public String getCities(@PathVariable Integer countryCode, Model model) {
		model.addAttribute("countryCode", countryCode);
		model.addAttribute("cities", countries.get(new Country("", countryCode)));
		return "task05";
	}

	// в фигурных скобках указаны переменный для определенной части адреса
	// значение из адреса будут записаны в переменные метода, которые помчечены
	// аннотацией @PathVariable
	@GetMapping("/countries/{country}/{city}")
	public String getFullCode(
			@PathVariable("country") Integer countryCode, 
			@PathVariable("city") Integer cityCode,
			Model model) {
		City city = countries.get(new Country("", countryCode)).stream().filter(c -> c.getCode() == cityCode)
				.findFirst().orElseGet(City::new);
		model.addAttribute("countryCode", countryCode);
		model.addAttribute("cityCode", cityCode);
		model.addAttribute("cityName", city.getName());
		return "task05";
	}
}