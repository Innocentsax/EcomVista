package dev.Innocent.ecomvista.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer productId;
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
