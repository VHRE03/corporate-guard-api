package com.vhre.corporateguardapi.common.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
// Indica que esta clase no es una tabla, pero sus hijos si heredan las columnas
@MappedSuperclass
// Activa la auditoria automatica de Spring Data JPA
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    // serialVersionUID es recomendado para serializacion de cache (Redis/Hazelcast)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;
}
