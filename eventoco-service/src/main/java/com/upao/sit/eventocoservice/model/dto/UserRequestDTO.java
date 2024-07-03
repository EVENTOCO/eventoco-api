package com.upao.sit.eventocoservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres")
    private String username;

    @NotBlank(message = "La contraseña del usuario no puede estar vacio")
    private String password;

    @NotBlank(message = "El email de usuario no puede estar vacio")
    @Email(message = "El correo debe ser válido")
    private String email;

    @NotBlank(message = "El número de teléfono de usuario no puede estar vacio")
    private String phone;

    @NotNull(message = "La fecha de cumpleaños no puede estar vacio")
    private LocalDate birthday;
}
