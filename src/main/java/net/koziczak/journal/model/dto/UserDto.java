package net.koziczak.journal.model.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    @NotBlank
    private String login;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6)
//   TODO  - dodac np. @Pattern(regexp = "[A-Z]+")

    private String password;
    private String password_repeat;

    public UserDto(String login, String email, String password) {
    }
}
