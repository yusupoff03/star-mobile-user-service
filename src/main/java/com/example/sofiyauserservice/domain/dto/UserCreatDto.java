package com.example.sofiyauserservice.domain.dto;

import com.example.sofiyauserservice.domain.entity.user.RoleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String email;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String password;
    private RoleEntity role;
}
