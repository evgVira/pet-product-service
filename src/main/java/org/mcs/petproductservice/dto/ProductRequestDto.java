package org.mcs.petproductservice.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
}
