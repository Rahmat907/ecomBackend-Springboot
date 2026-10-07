package com.backendapi.api.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendapi.api.dtos.respdto.OrderResponse;
import com.backendapi.api.service.OrderService;

import lombok.RequiredArgsConstructor;
@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/order")
public class OrderController {

    public final OrderService orderService;

    @PostMapping ("/")
    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-User-ID") String userId){
        Optional<OrderResponse> order = orderService.createOrder(userId);
        return order.map(orderResponse-> new ResponseEntity<>(orderResponse,HttpStatus.CREATED))
        .orElseGet(()-> ResponseEntity.notFound().build());
    }
    // Actuator 
    // features - Built in endpoints 
    // Ablity to view real time metrics 
    // customizable   
}
