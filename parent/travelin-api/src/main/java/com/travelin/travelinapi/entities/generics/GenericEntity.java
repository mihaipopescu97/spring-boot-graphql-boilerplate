package com.travelin.travelinapi.entities.generics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/21/2022, Wed
 **/
@MappedSuperclass
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class GenericEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "travelin.id_sequence", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Version
    @Column(name = "DATABASE_VERSION", nullable = false)
    private Long databaseVersion;

    /**
     * Time of entity creation.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", updatable = false, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    /**
     * Time of last update
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @PrePersist
    private void setUpdateDate() {
        updateDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericEntity genericEntity)) return false;
        return getId().equals(genericEntity.getId()) && getDatabaseVersion().equals(genericEntity.getDatabaseVersion()) && getCreateDate().equals(genericEntity.getCreateDate()) && Objects.equals(getUpdateDate(), genericEntity.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDatabaseVersion(), getCreateDate(), getUpdateDate());
    }
}
