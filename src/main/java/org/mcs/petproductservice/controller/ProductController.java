package org.mcs.petproductservice.controller;

import lombok.RequiredArgsConstructor;
import org.mcs.petproductservice.dto.ProductRequestDto;
import org.mcs.petproductservice.dto.ProductResponseDto;
import org.mcs.petproductservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-service")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    public ProductResponseDto getProductByProductId(@PathVariable("productId") UUID productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.addProduct(productRequestDto);
    }

    @PatchMapping("/quantity/delete/{productId}")
    public void deleteProductQuantityById(@PathVariable("productId") UUID productId, @RequestParam Integer quantity){
        productService.deleteProductQuantityById(productId, quantity);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable("productId") UUID productId){
        productService.deleteProductById(productId);
    }
}
