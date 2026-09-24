package dev.harrish.EcomProductService.service;

import dev.harrish.EcomProductService.entity.Category;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.CategoryNotFoundException;
import dev.harrish.EcomProductService.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImplTest
{
    @Mock //@Mock for all dependencies
    private CategoryRepository categoryRepository;

    @InjectMocks //@InjectMocks for the actual class we are testing
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup()
    {
        MockitoAnnotations.initMocks(this); //Not required nowadays, it initialises and adds all the required mocks
    }

    //generally line coverage percentage should be 90%
    //test methods are always public void
    @Test
    public void testGetTotalPriceForMultipleProductsUnderCategory() //this method will return the total cost for all products under a category
    {
        //arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(getCategoryMockData());
        double expectedTotalCost = 300.00;

        //act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //assert -> all the checks
        Assertions.assertEquals(actualTotalCost, expectedTotalCost);
    }

    @Test
    public void testGetTotalPriceForZeroProductsUnderCategory()
    {
        //arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(getCategoryMockDataWithZeroProducts());
        double expectedTotalCost = 0;

        //act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);

        //assert
        Assertions.assertEquals(actualTotalCost, expectedTotalCost);
        Mockito.verify(categoryRepository).findById(categoryId);
    }

    @Test
    public void testCategoryNotFoundExceptionThrown()
    {
        //arrange
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        //act and assert
        Assertions.assertThrows(CategoryNotFoundException.class, () -> categoryService.getTotalPriceForCategory(categoryId));
    }

    private Optional<Category> getCategoryMockDataWithZeroProducts()
    {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");
        List <Product> products = new ArrayList<>();
        category.setProducts(products);
        return Optional.of(category);
    }

    private Optional<Category> getCategoryMockData()
    {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setTitle("Product1");
        product1.setPrice(100.00);
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setTitle("Product2");
        product2.setPrice(100.00);
        product2.setCategory(category);

        Product product3 = new Product();
        product3.setId(UUID.randomUUID());
        product3.setTitle("Product3");
        product3.setPrice(100.00);
        product3.setCategory(category);

        List <Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        category.setProducts(products);
        return Optional.of(category);
    }
}
