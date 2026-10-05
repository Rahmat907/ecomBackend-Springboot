package com.backendapi.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendapi.api.model.CartItemsModel;
import com.backendapi.api.model.UserModel;
import com.backendapi.api.model.ProductModel;

@Repository 
public interface CartItemsRepo extends JpaRepository<CartItemsModel,Long> {
    CartItemsModel findByUserAndProduct(UserModel user, ProductModel productModel);
    void deleteByUserAndProduct(UserModel user, ProductModel product);
    List<CartItemsModel> findByUser(UserModel user);
    void deleteByUser(UserModel user);
}
