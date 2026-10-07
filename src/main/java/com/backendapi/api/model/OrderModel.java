package com.backendapi.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.backendapi.api.model.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Table( name = "orders")
public class OrderModel {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne 
    @JoinColumn (name = "user_id", nullable = false)
    private UserModel userModel;
    
    @Enumerated (EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
    
    @OneToMany (mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItems> items= new ArrayList<>();
    private BigDecimal totalAmount;

    @CreationTimestamp 
    private LocalDateTime createdAt;
    @UpdateTimestamp 
    private LocalDateTime updatedAt;

}
