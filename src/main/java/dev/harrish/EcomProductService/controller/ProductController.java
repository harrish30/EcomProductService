package dev.harrish.EcomProductService.controller;

import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.InvalidInputException;
import dev.harrish.EcomProductService.exception.RandomException;
import dev.harrish.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController
{
    @Autowired
    @Qualifier("productService")
    private ProductService productService; //field injection

    //mapping and redirection to a particular method inside a controller is done by dispatcher servlet
    //handler mapping stores the mapping
    @GetMapping("/product")
    public ResponseEntity getAllProducts()
    {
        List <FakeStoreProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable("id") int id)
    {
        if(id < 1)
        {
            throw new InvalidInputException("The input is not correct");
        }
        FakeStoreProductResponseDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/productexception")
    public ResponseEntity getProductException()
    {
        throw new RandomException("Exception from product");
    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody Product product)
    {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
}
