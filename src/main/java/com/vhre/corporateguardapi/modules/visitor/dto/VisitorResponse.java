package com.vhre.corporateguardapi.modules.visitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO inmutable para la respuesta de visitantes.
 * Se utilizan Records para evitar boilerplate code.
 */
@Schema(
        description = "Respuesta pública con la información del visitante.",
        name = "VisitorResponse"
)
public record VisitorResponse(
        @Schema(description = "Identificador único del sistema.", example = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11")
        UUID id,

        @Schema(description = "Número de documento", example = "1020304050")
        String documentNumber,

        @Schema(description = "Nombre completo formateado.", example = "Roberto Gómez Bolaños")
        String fullName,

        @Schema(description = "Empresa.", example = "Tech Solutions S.A.")
        String company,

        @Schema(description = "Teléfono.", example = "+525512345678")
        String phoneNumber,

        @Schema(description = "Fecha de registro en el sistema.")
        LocalDateTime createdAt
) {
}
