package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.client.FakeStoreClient;
import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.NoProductPresentException;
import dev.harrish.EcomProductService.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl
{
    @Autowired
    private FakeStoreClient fakeStoreClient;

    public List <FakeStoreProductResponseDTO> getAllProducts()
    {
        List <FakeStoreProductResponseDTO> fakeStoreProducts = fakeStoreClient.getAllProducts();
        if(fakeStoreProducts == null)
        {
            throw new NoProductPresentException("No products are found");
        }
        return fakeStoreProducts;
    }

    public FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException
    {
        FakeStoreProductResponseDTO fakeStoreProduct = fakeStoreClient.getProductById(productId);
        if(fakeStoreProduct == null)
        {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        return fakeStoreProduct;
    }

    public Product createProduct(Product product) {
        return null;
    }

    public Product updateProduct(Product updatedProduct, int productId) {
        return null;
    }

    public boolean deleteProduct(int productId) {
        return false;
    }
}
