package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.CreateProductRequestDTO;
import dev.harrish.EcomProductService.dto.ProductResponseDTO;
import dev.harrish.EcomProductService.entity.Category;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.CategoryNotFoundException;
import dev.harrish.EcomProductService.exception.ProductNotFoundException;
import dev.harrish.EcomProductService.mapper.ProductEntityDTOMapper;
import dev.harrish.EcomProductService.repository.CategoryRepository;
import dev.harrish.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productService")
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean deleteProduct(UUID productId)
    {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public List <ProductResponseDTO> getAllProducts()
    {
        List <Product> savedProducts = productRepository.findAll();
        List <ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for(Product product : savedProducts)
        {
            productResponseDTOs.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOs;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException
    {
        //basic code to implement null check
//        Product savedProduct = productRepository.findById(productId).get();
//        if(savedProduct == null)
//        {
//            throw new ProductNotFoundException("Product not found for id: " + productId);
//        }
//        return savedProduct;

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id: " + productId)
        );
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO)
    {
        Product savedProduct = ProductEntityDTOMapper.convertCreateProductRequestDTOtoProduct(createProductRequestDTO);
        Category savedCategory = categoryRepository.findById(createProductRequestDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found for id: " + createProductRequestDTO.getCategoryId())
        );
        savedProduct.setCategory(savedCategory);
        savedProduct = productRepository.save(savedProduct);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(CreateProductRequestDTO updatedProduct, UUID productId)
    {
        Product savedProduct =  productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id: \" + productId")
        );
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setImageURL(updatedProduct.getImageURL());
        savedProduct.setPrice(updatedProduct.getPrice());
        savedProduct.setTitle(updatedProduct.getTitle());
        savedProduct = productRepository.save(savedProduct); //can't update rating since it's done by user and category change is not allowed
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO getProduct(String productName)
    {
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(productRepository.findProductByTitle(productName));
    }

    @Override
    public List<ProductResponseDTO> getProducts(double minPrice, double maxPrice)
    {
        List <Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        List <ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        for(Product product : products)
        {
            productResponseDTOs.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOs;
    }
}
