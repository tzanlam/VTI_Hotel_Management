package hotel.hotel_management.modal.entity.hotel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bar")
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;
}
