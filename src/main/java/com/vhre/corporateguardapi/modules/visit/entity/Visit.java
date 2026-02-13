package com.vhre.corporateguardapi.modules.visit.entity;

import com.vhre.corporateguardapi.common.base.BaseEntity;
import com.vhre.corporateguardapi.modules.visitor.entity.Visitor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "visits",
        indexes = {
                @Index(name = "idx_visit_visitor", columnList = "visitor_id"),
                @Index(name = "idx_visit_entry_time", columnList = "entry_time"),
                @Index(name = "idx_visit_active", columnList = "exit_time")
        }
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE visits SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Visit extends BaseEntity {
    /*
     * RELACIONES
     * 1. FetchType.LAZY: Obligatorio para performance. Solo trae el Visitor si lo pides explicitamente.
     * 2. optional = false: Integridad. No puede existir una visita sin visitante.
     * 3. ToString.Exclude: Evita que Lombok lea esta propiedad al imprimir logs (evita consultas extra a DB).
     */
    @NotNull()
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visitor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_visit_visitor"))
    @ToString.Exclude
    private Visitor visitor;

    @NotNull()
    @Column(name = "entry_time", nullable = false)
    public LocalDateTime entryTime;

    @Column(name = "exit_time", nullable = true)
    public LocalDateTime exitTime;

    @NotNull()
    @Size(max = 50)
    @Column(name = "host_name", nullable = false, length = 50)
    public String hostName;

    @NotNull()
    @Size(max = 250)
    @Column(name = "purpose", nullable = false, length = 250)
    public String purpose;

    /**
     * Implementacion segura de equals y hashcode
     * Lombok no debe generar esto en Entidades JPA
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == this || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Visitor visitor = (Visitor) o;
        return getId() != null && Objects.equals(getId(), visitor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
