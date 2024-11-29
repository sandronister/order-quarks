package org.sandroni.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
@Data
public class OrderDTO {

    private Long customerId;

    private String customerName;

    private Long productId;

    private BigDecimal orderValue;

    public void validateOrder() {
        if (this.customerId == null) {
            throw new IllegalArgumentException("Customer ID is required");
        }

        if (this.customerName == null || this.customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }

        if (this.productId == null) {
            throw new IllegalArgumentException("Product ID is required");
        }

        if (this.orderValue == null || this.orderValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Order value is required");
        }
    }

}
