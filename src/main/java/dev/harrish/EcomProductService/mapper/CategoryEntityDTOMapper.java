package dev.harrish.EcomProductService.mapper;

import dev.harrish.EcomProductService.dto.CategoryResponseDTO;
import dev.harrish.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.harrish.EcomProductService.dto.ProductResponseDTO;
import dev.harrish.EcomProductService.entity.Category;
import dev.harrish.EcomProductService.entity.Product;
import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDTOMapper
{
    public static CategoryResponseDTO convertCategoryToCategoryResponseDTO(Category category)
    {
        //call Product entity to ProductResponseDTO mapper
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(category.getId());
        categoryResponseDTO.setCategoryName(category.getName());
        List <Product> products = category.getProducts();
        List <ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        if(!(products == null || products.isEmpty()))
        {
            for(Product product: products)
            {
                productResponseDTOs.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
            }
        }
        categoryResponseDTO.setProducts(productResponseDTOs);
        return categoryResponseDTO;
    }

    public static Category convertCreateCategoryRequestDTOToCategory(CreateCategoryRequestDTO createCategoryRequestDTO)
    {
        Category category = new Category();
        category.setName(createCategoryRequestDTO.getCategoryName());
        return category;
    }
}