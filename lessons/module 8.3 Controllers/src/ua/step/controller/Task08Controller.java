package ua.step.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Aнотация @ResponseBody позволяет вернуть клиентe данные без использования
 * шаблона, например изображение или json
 */
@Controller
public class Task08Controller {
	@GetMapping("/task08")
	@ResponseBody
	public String detectDevice(HttpServletRequest req) {
		Device device = DeviceUtils.getCurrentDevice(req);
		String deviceType = "unknown";
		if (device != null) {
			if (device.isNormal()) {
				deviceType = "normal";
			} else if (device.isMobile()) {
				deviceType = "mobile";
			} else if (device.isTablet()) {
				deviceType = "tablet";
			}
		}
		return "Hello " + deviceType + " browser!";
	}
}