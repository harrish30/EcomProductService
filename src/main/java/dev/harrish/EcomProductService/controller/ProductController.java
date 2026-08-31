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
    public ResponseEntity getAllProducts()
    {
        List <Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") UUID id)
    {
        if(id == null)
        {
            throw new InvalidInputException("The input is not correct");
        }
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product)
    {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    //used for demo of controller advice
    @GetMapping("/productexception")
    public ResponseEntity getProductException()
    {
        throw new RandomException("Exception from product");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") UUID id)
    {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") UUID id, @RequestBody Product product)
    {
        Product updatedProduct = productService.updateProduct(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/name/{productName}")
    public ResponseEntity getProductByProductName(@PathVariable("productName") String productName)
    {
        Product product = productService.getProduct(productName);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{min}/{max}")
    public ResponseEntity getProductByPriceRange(@PathVariable("min") double minPrice, @PathVariable("max") double maxPrice)
    {
        List <Product> products = productService.getProducts(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}
