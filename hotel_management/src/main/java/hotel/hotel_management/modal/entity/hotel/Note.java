package hotel.hotel_management.modal.entity.hotel;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "evaluate")
    private String note;

}
