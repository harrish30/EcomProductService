package dev.harrish.EcomProductService.exception;

public class ProductNotFoundException extends RuntimeException
{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
