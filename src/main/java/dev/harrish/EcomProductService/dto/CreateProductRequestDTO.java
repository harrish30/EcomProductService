package dev.harrish.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateProductRequestDTO
{
    private String title;
    private double price;
    private String description;
    private String category;
    private String imageURL;
    private UUID categoryId;
}
