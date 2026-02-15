package com.vhre.corporateguardapi.modules.visitor.mapper;

import com.vhre.corporateguardapi.modules.visitor.dto.VisitorCreateRequest;
import com.vhre.corporateguardapi.modules.visitor.dto.VisitorResponse;
import com.vhre.corporateguardapi.modules.visitor.dto.VisitorUpdateRequest;
import com.vhre.corporateguardapi.modules.visitor.entity.Visitor;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface VisitorMapper {
    // Mapeo de creacion
    Visitor toEntity(VisitorCreateRequest request);

    // Mapeo de Respuesta (Incluye lógica custom para fullName)
    @Mapping(target = "fullName", expression = "java(visitor.getFirstName() + \" \" + visitor.getLastName())")
    VisitorResponse toResponse(Visitor visitor);

    // Mapeo de actualización (Partial Update)
    // Solo actualiza los campos que no vengan nulos en el DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(VisitorUpdateRequest request, @MappingTarget Visitor entity);
}
