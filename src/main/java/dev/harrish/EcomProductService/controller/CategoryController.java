package dev.harrish.EcomProductService.controller;

import dev.harrish.EcomProductService.dto.CategoryResponseDTO;
import dev.harrish.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.harrish.EcomProductService.exception.InvalidInputException;
import dev.harrish.EcomProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@Qualifier("categoryService")
@RequestMapping("/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity <List<CategoryResponseDTO>> getAllCategories()
    {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity <CategoryResponseDTO> getCategoryById(@PathVariable("id") UUID categoryId)
    {
        if(categoryId == null)
        {
            throw new InvalidInputException("The input is not correct");
        }
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity <CategoryResponseDTO> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO)
    {
        if(createCategoryRequestDTO.getCategoryName() == null)
        {
            throw new InvalidInputException("The input is not correct");
        }
        return ResponseEntity.ok(categoryService.createCategory(createCategoryRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity <CategoryResponseDTO> updateCategory(@PathVariable ("id") UUID categoryId, @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO)
    {
        if(createCategoryRequestDTO.getCategoryName() == null || categoryId == null)
        {
            throw new InvalidInputException("The input is not correct");
        }
        return ResponseEntity.ok(categoryService.updateCategory(createCategoryRequestDTO, categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Boolean> deleteCategory(@PathVariable ("id") UUID categoryId)
    {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }

    @GetMapping("/totalPrice/{categoryId}")
    public ResponseEntity <Double> getTotalPriceForAllProducts(@PathVariable("categoryId") UUID categoryId)
    {
        return ResponseEntity.ok(categoryService.getTotalPriceForCategory(categoryId));
    }


}
