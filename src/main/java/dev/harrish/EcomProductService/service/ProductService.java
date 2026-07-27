package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService
{
    List <FakeStoreProductResponseDTO> getAllProducts();
    Product getProduct (int productId);
    Product createProduct (Product product);
    Product updateProduct(Product updatedProduct, int productId);
    boolean deleteProduct(int productId);
}
