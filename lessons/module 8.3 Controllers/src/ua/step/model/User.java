package ua.step.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {
	@NotBlank
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
	private String name;
	
	@NotNull
	@Email
	private String email;

	@Min(value = 18)
	@Max(value = 60)
	@Digits(fraction = 0, integer = 3)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}