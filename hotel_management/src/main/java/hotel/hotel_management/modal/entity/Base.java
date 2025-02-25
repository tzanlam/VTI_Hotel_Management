package hotel.hotel_management.modal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Base {
    @Column(updatable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
        modified = null;
    }

    @PreUpdate
    public void preUpdate() {
        modified = LocalDateTime.now();
    }
}
