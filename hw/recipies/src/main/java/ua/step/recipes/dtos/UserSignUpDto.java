package ua.step.recipes.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserSignUpDto {
    private String login;
    private String password;
    private String confirmPassword;
    private List<String> roles;
}
