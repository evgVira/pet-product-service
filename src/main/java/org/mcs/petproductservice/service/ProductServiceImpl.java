package org.mcs.petproductservice.service;

import lombok.RequiredArgsConstructor;
import org.mcs.petproductservice.dto.ProductRequestDto;
import org.mcs.petproductservice.dto.ProductResponseDto;
import org.mcs.petproductservice.mapper.ProductMapper;
import org.mcs.petproductservice.model.Product;
import org.mcs.petproductservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${product.discount.status}")
    private Boolean discountStatus;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    @Override
    public ProductResponseDto getProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElse(null);
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    @Transactional
    public void addProduct(ProductRequestDto productRequestDto){
        Product product = productMapper.toProduct(productRequestDto);
        product.setCreatedDt(Instant.now());
        product.setDiscount(discountStatus);
        product.setUniqProductId(UUID.randomUUID().toString());
        productRepository.save(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> response = products.stream()
                .map(product -> productMapper.toProductResponseDto(product))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    @Transactional
    public void deleteProductQuantityById(UUID productId, Integer deleteCount){
        Product product = productRepository.findById(productId).orElse(null);
        int currentProductQuantity = product.getQuantity();
        if(currentProductQuantity > deleteCount){
            product.setQuantity(currentProductQuantity - deleteCount);
        }else {
            product.setQuantity(0);
        }
        product.setUpdatedDt(Instant.now());
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProductById(UUID productId){
        productRepository.deleteById(productId);
    }
}
