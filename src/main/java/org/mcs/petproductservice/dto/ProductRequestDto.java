package org.mcs.petproductservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "описание товара")
public class ProductRequestDto {

    @Schema(description = "название товара")
    @NotBlank(message = "название товара не указано")
    private String name;

    @Schema(description = "описание товара")
    @NotBlank(message = "описание товара не указано")
    private String description;

    @Schema(description = "цена товара")
    @NotBlank(message = "цена товара не указана")
    private BigDecimal price;
}
