package com.backendapi.api.service;

import com.backendapi.api.service.CartService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.backendapi.api.repo.OrderRepo;
import com.backendapi.api.repo.UserRepo;
import com.backendapi.api.dtos.respdto.OrderResponse;
import lombok.RequiredArgsConstructor;
import com.backendapi.api.model.CartItemsModel;
import com.backendapi.api.model.UserModel;
import com.backendapi.api.service.UserService;
@Service 
@RequiredArgsConstructor 
public class OrderService {
    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final UserService userService;
    private final UserRepo userRepo;
    public Optional<OrderResponse> createOrder(String userId){
            // validate for cart items 
            List<CartItemsModel> cart = cartService.fetchCartItems(userId);
            if(cart.isEmpty()){}
            // validate for user
            Optional<UserModel> userOpt =  userRepo.findById(Long.valueOf(userId));
            
            if(userOpt.isEmpty()){
                return Optional.empty();
            }
            UserModel user = userOpt.get() ;

            // calculate totalPrice 
            // create order 
            // clear the cart 
        return null;
    }
}
