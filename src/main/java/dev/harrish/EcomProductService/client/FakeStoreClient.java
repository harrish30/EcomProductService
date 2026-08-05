package dev.harrish.EcomProductService.client;

import dev.harrish.EcomProductService.dto.FakeStoreCartResponseDTO;
import dev.harrish.EcomProductService.dto.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class FakeStoreClient
{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.base.url}") //this annotation will fetch the value from application properties and inject that value to this variable
    private String fakeStoreAPIBaseURL;
    @Value("${fakestore.api.product.path}")
    private String fakeStoreAPIProductPath;
    @Value("/carts?userId=")
    private String fakeStoreAPICartForUser;

    public List <FakeStoreProductResponseDTO> getAllProducts()
    {
        String fakeStoreGetAllProductURL = fakeStoreAPIBaseURL.concat(fakeStoreAPIProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        //RestTemplate is used to communicate with 3rd party APIs
        ResponseEntity <FakeStoreProductResponseDTO[]> productResponseList =
                restTemplate.getForEntity(fakeStoreGetAllProductURL, FakeStoreProductResponseDTO[].class);
        //RestTemplate is too old that it supports Array, we can still get arraylist but it's quite complicated
        return List.of(productResponseList.getBody());
    }

    public FakeStoreProductResponseDTO getProductById(int id)
    {
        String fakeStoreGetProductByIdURL = fakeStoreAPIBaseURL.concat(fakeStoreAPIProductPath).concat("/" + id);
        //URL -> https://fakestoreapi.com/products/{id}
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(fakeStoreGetProductByIdURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }

    public List<FakeStoreCartResponseDTO> getCartByUserId(int userId)
    {
        if(userId < 1)
        {
            return null;
        }
        String fakeStoreGetCartForUserURL = fakeStoreAPIBaseURL.concat(fakeStoreAPICartForUser).concat(String.valueOf(userId));
        //URL -> https://fakestoreapi.com/carts?userId=1 -> getCartByUserId.... ?userId -> query param discussed later
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity <FakeStoreCartResponseDTO[]> cartResponse =
                restTemplate.getForEntity(fakeStoreGetCartForUserURL, FakeStoreCartResponseDTO[].class);
        return List.of(cartResponse.getBody());
    }
}

//restTemplate.getForEntity(fakeStoreGetAllProductURL, FakeStoreProductResponseDTO[].class);
//getForEntity(urlForAPI, the object in which you want the response)