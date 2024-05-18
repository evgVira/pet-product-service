package org.mcs.petproductservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mcs.petproductservice.dto.ProductRequestDto;
import org.mcs.petproductservice.dto.ProductResponseDto;
import org.mcs.petproductservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product-service")
@RequiredArgsConstructor
@Tag(name = "ProductController", description = "Контроллер товара")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    @Operation(summary = "Получение всех товаров")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "получение описания товара по его id")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDto getProductByProductId(@Parameter(description = "id товара") @PathVariable("productId") UUID productId) {
        return productService.getProductById(productId);
    }

    @PostMapping("/add")
    @Operation(summary = "добавление нового товара")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        productService.addProduct(productRequestDto);
    }

    @PatchMapping("/quantity/delete/{productId}")
    @Operation(summary = "удалить определеннное кол-во товаров из базы")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductQuantityById(@Parameter(description = "id товара")@PathVariable("productId") UUID productId,
                                          @Parameter(description = "кол-во которое нужно удалить из базы") @RequestParam Integer quantity){
        productService.deleteProductQuantityById(productId, quantity);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "полностью удалить товар из базы")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("productId") UUID productId){
        productService.deleteProductById(productId);
    }
}
