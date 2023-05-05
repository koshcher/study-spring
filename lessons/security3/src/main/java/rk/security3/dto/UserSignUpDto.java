package rk.security3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignUpDto {
    private String username;
    private String password;
    private String confirmPassword;
}
