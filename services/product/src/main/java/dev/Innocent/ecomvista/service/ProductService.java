package dev.Innocent.ecomvista.service;

import dev.Innocent.ecomvista.DTO.request.ProductPurchaseRequest;
import dev.Innocent.ecomvista.DTO.request.ProductRequest;
import dev.Innocent.ecomvista.DTO.response.ProductPurchaseResponse;
import dev.Innocent.ecomvista.DTO.response.ProductResponse;
import dev.Innocent.ecomvista.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        return null;
    }

    public ProductResponse findById(Integer productId) {
        return null;
    }

    public List<ProductResponse> findAll() {
        return null;
    }
}
