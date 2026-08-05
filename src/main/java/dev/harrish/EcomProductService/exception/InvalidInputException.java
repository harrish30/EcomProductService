package dev.harrish.EcomProductService.exception;

public class InvalidInputException extends RuntimeException
{
    public InvalidInputException(String message)
    {
        super(message);
    }
}
