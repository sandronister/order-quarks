package org.sandroni.entity;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String customerName;

    private Long productId;

    private BigDecimal orderValue;
}
