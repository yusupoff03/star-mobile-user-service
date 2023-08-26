package com.example.sofiyauserservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SellerDto {
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String password;
    @NotNull(message = "LastName cannot be null")
    @NotBlank(message = "LastName cannot be blank")
    private String lastName;
    @NotNull(message = "FatherName cannot be null")
    @NotBlank(message = "FatherName cannot be blank")
    private String fatherName;
    @NotNull(message = "BirthDate cannot be null")
    private LocalDate birthDate;
    @NotBlank(message = "Passport number cannot be blank")
    @NotNull(message = "Passport number cannot be null")
    @Pattern(
            regexp = "^[A-Z]{2}\\d{7}$",
            message = "does not match the template(AA1234567)")
    @NotEmpty(message = "Passport can not be empty")
    private String passportNumber;
    @NotNull(message = "Phone number cannot be null")
    @Pattern(regexp = "^\\+998 \\d{2} \\d{3} \\d{4}$",
    message = "does not match the template (+998991234567)")
    private String phoneNumber;
}
