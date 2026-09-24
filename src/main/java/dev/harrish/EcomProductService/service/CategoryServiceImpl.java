package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.CategoryResponseDTO;
import dev.harrish.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.harrish.EcomProductService.entity.Category;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.CategoryNotFoundException;
import dev.harrish.EcomProductService.mapper.CategoryEntityDTOMapper;
import dev.harrish.EcomProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) throws CategoryNotFoundException
    {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id: " + categoryId)
        );
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories()
    {
        List <Category> categories = categoryRepository.findAll();
        List <CategoryResponseDTO> categoryResponseDTOs = new ArrayList<>();
        for(Category category: categories)
        {
            categoryResponseDTOs.add(CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category));
        }
        return categoryResponseDTOs;
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO createCategoryRequestDTO)
    {
        Category category = CategoryEntityDTOMapper.convertCreateCategoryRequestDTOToCategory(createCategoryRequestDTO);
        categoryRepository.save(category);
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId) throws CategoryNotFoundException
    {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id: " + categoryId)
        );
        savedCategory.setName(createCategoryRequestDTO.getCategoryName());
        categoryRepository.save(savedCategory);
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(savedCategory);
    }

    @Override
    public boolean deleteCategory(UUID categoryId)
    {
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId)
    {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id: " + categoryId)
        );
        List <Product> products = category.getProducts();
        if(products.isEmpty())
        {
            return 0;
        }
        else
        {
            double sum = 0;
            for(Product product: products)
            {
                sum += product.getPrice();
            }
            return sum;
        }
    }
}
