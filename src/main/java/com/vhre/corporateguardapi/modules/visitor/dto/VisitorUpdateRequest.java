package com.vhre.corporateguardapi.modules.visitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO inmutable para la actualización de visitantes.
 * Se utilizan Records para evitar boilerplate code.
 */
@Schema(
        description = "Objeto de transferencia para actualizar datos de un visitante existente.",
        name = "VisitorUpdateRequest"
)
public record VisitorUpdateRequest(
        @Schema(description = "Actualizar el nombre.", example = "Roberto Carlos")
        @Size(max = 50)
        String firstName,

        @Schema(description = "Actualizar apellido.", example = "Gómez")
        @Size(max = 50)
        String lastName,

        @Schema(description = "Actualizar empresa.", example = "Freelance.")
        @Size(max = 100)
        String company,

        @Schema(description = "Actualizar número de teléfono.", example = "+527224635246")
        @Size(max = 20)
        @Pattern(regexp = "^\\+?[0-9]*$")
        String phoneNumber
) {
}
