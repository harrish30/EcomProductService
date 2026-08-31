package dev.harrish.EcomProductService.mapper;

import dev.harrish.EcomProductService.dto.ProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;

public class ProductEntityDTOMapper
{
    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product)
    {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//        productResponseDTO.setCategory(product.getCategory());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setImageURL(product.getImageURL());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setRating(product.getRating());
        productResponseDTO.setTitle(product.getTitle());
        return productResponseDTO;
    }
}
