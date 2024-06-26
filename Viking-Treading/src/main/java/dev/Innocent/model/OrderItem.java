package dev.Innocent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;

    @ManyToOne
    private Coin coin;
    private double buyPrice;
    private double sellPrice;

    @OneToOne
    @JsonIgnore
    private Order order;
}
