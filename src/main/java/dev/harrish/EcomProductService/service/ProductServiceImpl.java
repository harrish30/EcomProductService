package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.ProductNotFoundException;
import dev.harrish.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean deleteProduct(UUID productId)
    {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public List <Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID productId) throws ProductNotFoundException
    {
        //basic code to implement null check
//        Product savedProduct = productRepository.findById(productId).get();
//        if(savedProduct == null)
//        {
//            throw new ProductNotFoundException("Product not found for id: " + productId);
//        }
//        return savedProduct;

        return productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id: \" + productId")
        );
    }

    @Override
    public Product createProduct(Product product)
    {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product updatedProduct, UUID productId) {
        Product savedProduct =  productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id: \" + productId")
        );
        savedProduct.setCategory(updatedProduct.getCategory());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setImageURL(updatedProduct.getImageURL());
        savedProduct.setPrice(updatedProduct.getPrice());
        savedProduct.setRating(updatedProduct.getRating());
        savedProduct.setTitle(updatedProduct.getTitle());
        savedProduct = productRepository.save(savedProduct);
        return savedProduct;
    }

    @Override
    public Product getProduct(String productName)
    {
        return productRepository.findProductByTitle(productName);
    }

    @Override
    public List<Product> getProducts(double minPrice, double maxPrice)
    {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
