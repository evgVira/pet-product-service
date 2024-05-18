package org.mcs.petproductservice.service;

import org.mcs.petproductservice.dto.ProductRequestDto;
import org.mcs.petproductservice.dto.ProductResponseDto;
import org.mcs.petproductservice.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDto getProductById(UUID productId);

    void addProduct(ProductRequestDto productRequestDto);

    List<ProductResponseDto> getAllProducts();

    void deleteProductQuantityById(UUID productId, Integer deleteCount);

    void deleteProductById(UUID productId);
}
