package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ProductService
{
    List <Product> getAllProducts();
    Product getProduct (UUID productId) throws ProductNotFoundException;
    Product createProduct (Product product);
    Product updateProduct(Product updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    Product getProduct(String productName);
    List <Product> getProducts(double minPrice, double maxPrice);
}
