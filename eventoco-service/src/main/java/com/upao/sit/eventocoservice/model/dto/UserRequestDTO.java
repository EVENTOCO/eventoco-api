package com.upao.sit.eventocoservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Size(message = "El número de cuenta debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "El email de usuario no puede estar vacio")
    @Email(message = "El correo debe ser válido")
    private String email;

    @NotBlank(message = "El número de teléfono de usuario no puede estar vacio")
    private String phone;

    @NotBlank(message = "La fecha de cumpleaños no puede estar vacio")
    private LocalDate birthday;
}
