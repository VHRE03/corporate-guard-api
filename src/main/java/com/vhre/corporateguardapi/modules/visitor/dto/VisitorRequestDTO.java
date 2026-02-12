package com.vhre.corporateguardapi.modules.visitor.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VisitorRequestDTO {
    @NotBlank(message = "El numero de documentos es obligatorio")
    @Size(min = 5, max = 20, message = "El documento debe de tener  entre 5 y 20 caracteres")
    private String documentNumber;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;

    @NotBlank(message = "El nombre de la compania no puede  estar vacio")
    @Size(min = 1, max = 100, message = "El nombre de la compania debe tener en 1 y 100 caracteres")
    private String company;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "El teléfono solo debe contener números")
    private String phoneNumber;
}
