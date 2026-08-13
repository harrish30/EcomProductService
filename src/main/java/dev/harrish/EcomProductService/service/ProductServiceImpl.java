package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.ProductNotFoundException;
import dev.harrish.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }

    @Override
    public List<FakeStoreProductResponseDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product createProduct(Product product)
    {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product updatedProduct, int productId) {
        return null;
    }
}
