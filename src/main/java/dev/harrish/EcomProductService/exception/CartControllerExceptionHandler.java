package dev.harrish.EcomProductService.exception;

import dev.harrish.EcomProductService.controller.CartController;
import dev.harrish.EcomProductService.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = CartController.class)
public class CartControllerExceptionHandler
{
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity handleCartNotFoundException(CartNotFoundException cartNotFoundException)
    {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                cartNotFoundException.getMessage(),
                404
        );
        return new ResponseEntity <> (exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
}
