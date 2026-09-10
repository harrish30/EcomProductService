package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.CategoryResponseDTO;
import dev.harrish.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.harrish.EcomProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId)
    {
        return null;
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories()
    {
        return List.of();
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO createCategoryRequestDTO)
    {
        return null;
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId)
    {
        return null;
    }

    @Override
    public boolean deleteCategory(UUID categoryId)
    {
        return false;
    }
}
