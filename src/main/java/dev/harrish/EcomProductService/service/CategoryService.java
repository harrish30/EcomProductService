package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.dto.CategoryResponseDTO;
import dev.harrish.EcomProductService.dto.CreateCategoryRequestDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService
{
    CategoryResponseDTO getCategory(UUID categoryId);
    List <CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory(CreateCategoryRequestDTO createCategoryRequestDTO);
    CategoryResponseDTO updateCategory(CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId);
    boolean deleteCategory(UUID categoryId);
    double getTotalPriceForCategory(UUID categoryId);
}
