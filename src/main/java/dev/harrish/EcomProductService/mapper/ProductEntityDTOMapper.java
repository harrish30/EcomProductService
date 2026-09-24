package dev.harrish.EcomProductService.mapper;

import dev.harrish.EcomProductService.dto.CreateProductRequestDTO;
import dev.harrish.EcomProductService.dto.ProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;

public class ProductEntityDTOMapper
{
    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product)
    {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProductId(product.getId());
        productResponseDTO.setCategory(product.getCategory().getName());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setImageURL(product.getImageURL());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setRating(product.getRating());
        productResponseDTO.setTitle(product.getTitle());
        return productResponseDTO;
    }

    public static Product convertCreateProductRequestDTOtoProduct(CreateProductRequestDTO createProductRequestDTO)
    {
        Product product = new Product();
        product.setTitle(createProductRequestDTO.getTitle());
        product.setRating(0);
        product.setPrice(createProductRequestDTO.getPrice());
        product.setImageURL(createProductRequestDTO.getImageURL());
        product.setDescription(createProductRequestDTO.getDescription());
        return product;
    }
}
