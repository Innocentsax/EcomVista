package dev.Innocent.ecomvista.service;

import dev.Innocent.ecomvista.DTO.request.ProductRequest;
import dev.Innocent.ecomvista.product.Category;
import dev.Innocent.ecomvista.product.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder()
                        .id(productRequest.categoryId())
                        .build())
                .build();
    }
}
