package dev.harrish.EcomProductService.controller;

import dev.harrish.EcomProductService.client.FakeStoreClient;
import dev.harrish.EcomProductService.dto.fakeStoreDTOs.FakeStoreCartResponseDTO;
import dev.harrish.EcomProductService.exception.CartNotFoundException;
import dev.harrish.EcomProductService.exception.RandomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//Cart Controller was created only to showcase the use of controller advice,
// in this code we are not doing anything related to cart.
@RestController
public class CartController
{
    @Autowired
    private FakeStoreClient fakeStoreClient;

    @GetMapping("/cart/{userId}")
    public ResponseEntity getCartForUser(@PathVariable("userId") int userId)
    {
        List<FakeStoreCartResponseDTO> cartResponse = fakeStoreClient.getCartByUserId(userId);
        if(cartResponse == null)
        {
            throw new CartNotFoundException("Cart not found for user id:" + userId);
        }
        return ResponseEntity.ok(cartResponse);
    }
    @GetMapping("/cartexception")
    public ResponseEntity getCartException()
    {
        throw new RandomException("Exception from cart");
    }
}
