package com.vhre.corporateguardapi.modules.visitor.entity;

import com.vhre.corporateguardapi.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Objects;

@Entity
@Table(
        name = "visitors",
        indexes = {
                // Indice explicito para busquedas rapidas por docuemento
                @Index(name = "idx_visitor_doc", columnList = "document_number", unique = true),
                // Indice compuesto para buscar por nombre completo
                @Index(name = "idx_vissitor_name", columnList = "last_name, first_name")
        }
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Implementacion del Soft Delete (Eliminacion logica)
@SQLDelete(sql = "UPDATE visitors SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Visitor extends BaseEntity {

    @NotNull()
    @Size(max = 20)
    @Column(name = "document_number", nullable = false, length = 20)
    private String documentNumber;

    @NotNull()
    @Size(max = 50)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotNull()
    @Size(max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Size(max = 100)
    @Column(name = "company", length = 100)
    private String company;

    @Size(max = 20)
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /**
     * Implementacion segura de equals y hashcode
     * Lombok no debe generar esto en Entidades JPA
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == this || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Visitor visitor = (Visitor) o;
        return getId()  != null && Objects.equals(getId(), visitor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
