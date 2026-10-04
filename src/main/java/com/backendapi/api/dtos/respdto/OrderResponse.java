package com.backendapi.api.dtos.respdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.backendapi.api.model.enums.OrderStatus;;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDTO> items;
    @CreationTimestamp 
    private LocalDateTime creatAt; 
}
