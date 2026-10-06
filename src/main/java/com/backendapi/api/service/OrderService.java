package com.backendapi.api.service;

import com.backendapi.api.service.CartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.backendapi.api.repo.OrderRepo;
import com.backendapi.api.repo.UserRepo;
import com.backendapi.api.dtos.respdto.OrderResponse;
import lombok.RequiredArgsConstructor;
import com.backendapi.api.model.CartItemsModel;
import com.backendapi.api.model.OrderItems;
import com.backendapi.api.model.OrderModel;
import com.backendapi.api.model.UserModel;
import com.backendapi.api.model.enums.OrderStatus;
import com.backendapi.api.service.UserService;
import com.backendapi.api.dtos.respdto.OrderItemDTO;
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
            BigDecimal totalPrice = cart.stream()
            .map(CartItemsModel :: getPrice)
            .filter(Objects :: nonNull)
            .reduce(BigDecimal.ZERO ,BigDecimal:: add); 
            // create order 
            OrderModel order = new OrderModel();
            order.setUserModel(user);
            order.setStatus(OrderStatus.CONFIRMED);
            order.setTotalAmount(totalPrice);
            List<OrderItems> orderItems = cart.stream().map(item -> new OrderItems(null,item.getProduct(),item.getQuantity(),item.getProduct().getPrice(),order)).toList();
            // clear the cart
            order.setItems(orderItems);
            OrderModel savedOrder  = orderRepo.save(order);

            cartService.clearCart(userId);

        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponse mapToOrderResponse(OrderModel order){
        OrderResponse orderDto = new OrderResponse(
        order.getOrderId(),
        order.getTotalAmount(),
        order.getStatus(),
        order.getItems().stream()
        .map(orderItem -> new OrderItemDTO(orderItem.getId(),orderItem.getProduct().getProductId(),orderItem.getQuantity(),orderItem.getPrice(),orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())))).toList(),
    order.getCreatedAt());
        return orderDto;
    }

}