package org.mcs.petproductservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductKafkaResponseDto {
    private String uniqProductId;
    private Integer quantity;
}
