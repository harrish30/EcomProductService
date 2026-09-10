package dev.harrish.EcomProductService.dto.fakeStoreDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO
{
    private int id; //make sure the variable names matches with the JSON key, since we are working with 3rd party APIs
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private FakeStoreProductRatingDTO rating;
}
//        {
//        "id": 0,
//        "title": "string",
//        "price": 0.1,
//        "description": "string",
//        "category": "string",
//        "image": "http://example.com",
//        "rating" : {
//              "rate" : 3.9,
//              "count" : 120
//        }
//        }
//
