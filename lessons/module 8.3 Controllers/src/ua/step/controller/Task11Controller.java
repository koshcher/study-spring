package ua.step.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Загрузка файлов
 */
@Controller
public class Task11Controller {
	@GetMapping("/uploadFile")
	public String getUploadFileForm(Model model) throws IOException {
		String rootPath = System.getProperty("catalina.home");
		List<String> fileNames = Files
				.list(Paths.get(rootPath))
				.filter(Files::isRegularFile).map(Path::getFileName)
				.map(s -> s.toString())
				.filter(p -> p.endsWith(".PNG") || p.endsWith(".png") || p.endsWith(".JPG") || p.endsWith(".jpg"))
				.collect(Collectors.toList());
		model.addAttribute("fileNames", fileNames);
		return "task11";
	}

	/**
	 * Загрузка изображения из корневой дериктории и подготовка его к отображению. В
	 * параметре аннотации @GetMapping используется регулярное выражение для того
	 * чтобы расширение файла попала в значение параметра
	 */
	@GetMapping("/images/{fileName:.+}")
	@ResponseBody
	public byte[] getImage(@PathVariable String fileName) throws IOException {
		String rootPath = System.getProperty("catalina.home");
		try (FileInputStream fis = new FileInputStream(rootPath + "/" + fileName)) {
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			return bytes;
		}
	}

	@PostMapping("/uploadFile")
	public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] fileBytes = file.getBytes();
				String rootPath = System.getProperty("catalina.home");
				System.out.println("Серевер корневая дериктория: " + rootPath);
				System.out.println("Имя загружаемого файла: " + file.getOriginalFilename());
				System.out.println("Тип загружаемого файла: " + file.getContentType());

				File newFile = new File(rootPath + File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
				stream.write(fileBytes);
				stream.close();

				System.out
						.println("Файл сохранен по адресу: " + rootPath + File.separator + file.getOriginalFilename());
			} catch (Exception e) {
				System.out.println("File upload is failed: " + e.getMessage());
			}
		} else {
			System.out.println("Загрузка файла прервана. Не выбран файл.");
		}
		return "redirect:/uploadFile";
	}
}