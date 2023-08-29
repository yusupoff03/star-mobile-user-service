package com.example.sofiyauserservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreatDto {
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*@gmail\\.com$",
            message = "Email didnt match")
    private String email;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String password;
}
