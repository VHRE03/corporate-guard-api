package com.vhre.corporateguardapi.modules.visitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO inmutable para la creación de visitantes.
 * Se utilizan Records para evitar boilerplate code.
 */
@Schema(
        description = "Objeto de transferencia para registrar un nuevo visitante.",
        name = "VisitorCreateRequest"
)
public record VisitorCreateRequest(
        @Schema(
                description = "Número de documento de identificación (DNI, Pasaporte, Cédula)",
                example = "1020304050",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El número de documento es obligatorio")
        @Size(min = 5, max = 20, message = "El documento debe tener entre 5 y 20 caracteres")
        String documentNumber,

        @Schema(
                description = "Primer nombre del visitante.",
                example = "Roberto",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
        String firstName,

        @Schema(
                description = "Apellidos del visitante.",
                example = "Gómez Bolaños",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no puede exceder 100 caracteres")
        String lastName,

        @Schema(
                description = "Empresa a la que representa el visitante.",
                example = "Tech Solutions S.A.",
                nullable = true
        )
        @Size(max = 100, message = "El nombre de la empresa no puede exceder 100 caracteres")
        String company,

        @Schema(
                description = "Número de teléfono de contacto.",
                example = "+527224637246",
                nullable = true
        )
        @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
        @Pattern(regexp = "^\\+?[0-9]*$", message = "El teléfono solo puede contener números y el símbolo +")
        String phoneNumber
) {
}
