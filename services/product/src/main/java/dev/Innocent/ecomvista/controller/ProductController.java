package dev.Innocent.ecomvista.controller;

import dev.Innocent.ecomvista.DTO.request.ProductPurchaseRequest;
import dev.Innocent.ecomvista.DTO.request.ProductRequest;
import dev.Innocent.ecomvista.DTO.response.ProductPurchaseResponse;
import dev.Innocent.ecomvista.DTO.response.ProductResponse;
import dev.Innocent.ecomvista.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchaseProduct(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
