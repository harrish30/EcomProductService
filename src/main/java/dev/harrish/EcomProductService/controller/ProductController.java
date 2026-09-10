package dev.harrish.EcomProductService.controller;

import dev.harrish.EcomProductService.dto.CreateProductRequestDTO;
import dev.harrish.EcomProductService.dto.ProductResponseDTO;
import dev.harrish.EcomProductService.entity.Product;
import dev.harrish.EcomProductService.exception.InvalidInputException;
import dev.harrish.EcomProductService.exception.RandomException;
import dev.harrish.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product") //base URL for all the APIs in this controller
public class ProductController
{
    @Autowired
    @Qualifier("productService")
    private ProductService productService; //field injection

    //mapping and redirection to a particular method inside a controller is done by dispatcher servlet
    //handler mapping stores the mapping
    @GetMapping
    public ResponseEntity <List <ProductResponseDTO>> getAllProducts() //better practice to specific this -> <List <ProductResponseDTO>>, so that it accepts of this type
    {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity <ProductResponseDTO> getProductById(@PathVariable("id") UUID id)
    {
        if(id == null)
        {
            throw new InvalidInputException("The input is not correct");
        }
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity <ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO)
    {
        return ResponseEntity.ok(productService.createProduct(createProductRequestDTO));
    }

    //used for demo of controller advice
    @GetMapping("/productexception")
    public ResponseEntity getProductException()
    {
        throw new RandomException("Exception from product");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Boolean> deleteProduct(@PathVariable("id") UUID id)
    {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <ProductResponseDTO> updateProduct(@PathVariable("id") UUID id, @RequestBody CreateProductRequestDTO createProductRequestDTO)
    {
        return ResponseEntity.ok(productService.updateProduct(createProductRequestDTO, id));
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity <ProductResponseDTO> getProductByProductName(@PathVariable("productName") String productName)
    {
        return ResponseEntity.ok(productService.getProduct(productName));
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity <List <ProductResponseDTO>> getProductByPriceRange(@PathVariable("min") double minPrice, @PathVariable("max") double maxPrice)
    {
        return ResponseEntity.ok(productService.getProducts(minPrice, maxPrice));
    }
}
