package com.backendapi.api.service;

import org.springframework.stereotype.Service;
import com.backendapi.api.repo.OrderRepo;
import com.backendapi.api.dtos.respdto.OrderResponse;
import lombok.RequiredArgsConstructor;
@Service 
@RequiredArgsConstructor 
public class OrderService {
    private final OrderRepo orderRepo;
    public OrderResponse createOrder(String userId){
            // validate for cart items 
            // validate for user
            // calculate totalPrice 
            // create order 
            // clear the cart 
        return null;
    }
}
