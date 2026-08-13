package dev.harrish.EcomProductService.exception;

import dev.harrish.EcomProductService.controller.ProductController;
import dev.harrish.EcomProductService.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProductController.class) //When Controller wants to throw an exception, controller advice is what it calls so that the dev can handle the exception
public class ProductControllerExceptionHandler
{
    @ExceptionHandler({ProductNotFoundException.class, NoProductPresentException.class}) //ProductNotFoundException is triggered by this annotation
    public ResponseEntity handleNoProductException(ProductPresentException productPresentException)
    {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                productPresentException.getMessage(),
                404
        );
        return new ResponseEntity <> (exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity handleInvalidInputException(InvalidInputException invalidInputException)
    {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                invalidInputException.getMessage(),
                400
        );
        return new ResponseEntity <> (exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RandomException.class)
    public ResponseEntity handleProductRandomException(RandomException randomException)
    {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                randomException.getMessage(),
                404
        );
        return new ResponseEntity <> (exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
}
