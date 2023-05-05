package ua.step.example;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {
    @Autowired
    private MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getInternationalPage(Locale locale, Model model)  {
        // add parametrized message from controller
        String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);
        model.addAttribute("message", welcome);
        
        // obtain locale from LocaleContextHolder
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("locale", currentLocale);
		return "home";
	}
}