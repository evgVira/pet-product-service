package org.mcs.petproductservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mcs.petproductservice.dto.ProductRequestDto;
import org.mcs.petproductservice.dto.ProductResponseDto;
import org.mcs.petproductservice.model.Product;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductResponseDto toProductResponseDto(Product product);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().randomUUID())")
    @Mapping(target = "quantity", expression = "java(100)")
    Product toProduct(ProductRequestDto productRequestDto);
}
