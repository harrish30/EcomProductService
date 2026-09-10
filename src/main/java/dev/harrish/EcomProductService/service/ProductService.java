package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.CreateProductRequestDTO;
import dev.harrish.EcomProductService.dto.ProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService
{
    List <ProductResponseDTO> getAllProducts();
    ProductResponseDTO getProduct (UUID productId) throws ProductNotFoundException;
    ProductResponseDTO createProduct (CreateProductRequestDTO product);
    ProductResponseDTO updateProduct(CreateProductRequestDTO updatedProduct, UUID productId);
    boolean deleteProduct(UUID productId);
    ProductResponseDTO getProduct(String productName);
    List <ProductResponseDTO> getProducts(double minPrice, double maxPrice);
}
