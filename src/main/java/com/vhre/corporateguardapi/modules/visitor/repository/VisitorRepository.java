package com.vhre.corporateguardapi.modules.visitor.repository;

import com.vhre.corporateguardapi.modules.visitor.entity.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, UUID> {
    // Metodo para verificar duplicados antes de crear
    boolean existsByDocumentNumber(String documentNumber);

    // Búsqueda por documento
    Optional<Visitor> findByDocumentNumber(String documentNumber);

    // Búsqueda flexible por nombre o apellido
    // Spring Data JPA crear la query automáticamente
    Page<Visitor> findByName(String firstName, String lastName, Pageable pageable);
}
