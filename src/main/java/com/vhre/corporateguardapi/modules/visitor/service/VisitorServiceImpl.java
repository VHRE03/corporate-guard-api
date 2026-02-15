package com.vhre.corporateguardapi.modules.visitor.service;

import com.vhre.corporateguardapi.modules.visitor.dto.VisitorCreateRequest;
import com.vhre.corporateguardapi.modules.visitor.dto.VisitorResponse;
import com.vhre.corporateguardapi.modules.visitor.dto.VisitorUpdateRequest;
import com.vhre.corporateguardapi.modules.visitor.entity.Visitor;
import com.vhre.corporateguardapi.modules.visitor.mapper.VisitorMapper;
import com.vhre.corporateguardapi.modules.visitor.repository.VisitorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Por defecto solo lectura para optimizar rendimiento
public class VisitorServiceImpl implements VisitorService{

    private final VisitorRepository visitorRepository;
    private final VisitorMapper visitorMapper;

    @Override
    @Transactional
    public VisitorResponse create(VisitorCreateRequest request)
    {
        if(visitorRepository.existsByDocumentNumber(request.documentNumber()))
        {
            throw new IllegalArgumentException("Ya existe un visitante con el documento: " + request.documentNumber());
        }

        Visitor entity = visitorMapper.toEntity(request);
        Visitor savedEntity = visitorRepository.save(entity);
        return visitorMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional
    public VisitorResponse update(UUID id, VisitorUpdateRequest request)
    {
        Visitor visitor = visitorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Visitante no encontrado"));

        // MapStruct aplica los cambios parciales sobre la entidad gestionada
        visitorMapper.updateEntityFromDto(request, visitor);

        // Al estar en una transacción, Hibernate detecta los cambios (Dirty Checking)
        // y hace el update automáticamente al cerrar la transacción, pero el save es explícito por claridad.
        return visitorMapper.toResponse(visitorRepository.save(visitor));
    }

    @Override
    public VisitorResponse getById(UUID id)
    {
        return visitorRepository.findById(id).map(visitorMapper::toResponse).orElseThrow(() -> new EntityNotFoundException("Visitante no encontrado"));
    }

    @Override
    @Transactional
    public void delete(UUID id){
        if (!visitorRepository.existsById(id))
        {
            throw new EntityNotFoundException("Visitante no encontrado para eliminar");
        }

        visitorRepository.deleteById(id); // Soft delete manejado por la entidad
    }

    @Override
    public Page<VisitorResponse> findAll(Pageable pageable)
    {
        return visitorRepository.findAll(pageable).map(visitorMapper::toResponse);
    }

    public Page<VisitorResponse> search(String query, Pageable pageable)
    {
        return visitorRepository.findByFullName(query, query, pageable).map(visitorMapper::toResponse);
    }
}
