package com.backendapi.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backendapi.api.dtos.reqdto.CartItemRequest;
import com.backendapi.api.model.CartItemsModel;
import com.backendapi.api.model.ProductModel;
import com.backendapi.api.model.UserModel;
import com.backendapi.api.repo.CartItemsRepo;
import com.backendapi.api.repo.ProductRepo;
import com.backendapi.api.repo.UserRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional 
public class CartService {
    public final CartItemsRepo cartItemsRepo;
    public final ProductRepo productRepo;
    public final UserRepo userRepo;

    public CartService (CartItemsRepo cartItemsRepo,ProductRepo productRepo,UserRepo userRepo){
        this.cartItemsRepo = cartItemsRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }
    public boolean addToCart(String userId, CartItemRequest cartItemRequest) {
        // look for product
        Optional<ProductModel> productOpt = productRepo.findById(cartItemRequest.getProductid());
        if (productOpt.isEmpty())
            return false;

        ProductModel productModel = productOpt.get();

        if (productModel.getStockQuantity() < cartItemRequest.getQuantity())
            return false;

        Optional<UserModel> userOptional = userRepo.findById(Long.parseLong(userId));

        if (userOptional.isEmpty())
            return false;

        UserModel user = userOptional.get();

        CartItemsModel existingcart = cartItemsRepo.findByUserAndProduct(user,productModel);
        if(existingcart != null){
            // update the qunatity
            existingcart.setQuantity(existingcart.getQuantity() + cartItemRequest.getQuantity());
            existingcart.setPrice(existingcart.getPrice().multiply(BigDecimal.valueOf(existingcart.getQuantity())));
        }else{
            // create new cart
            CartItemsModel newCart = new CartItemsModel();
            newCart.setProduct(productModel);
            newCart.setUser(user);
            newCart.setQuantity(cartItemRequest.getQuantity());

            cartItemsRepo.save(newCart);
        }
        return true;
    }
    public Boolean deleteCartItems(String userId, Long productId){
            Optional<ProductModel> productOpt = productRepo.findById(productId);
        if (productOpt.isEmpty())
            return false;

        Optional<UserModel> userOptional = userRepo.findById(Long.parseLong(userId));

        if (userOptional.isEmpty())
            return false;

       return userOptional.flatMap(
            user -> productOpt.map(product -> {
                cartItemsRepo.deleteByUserAndProduct(user,product);
                return true;
            })
        ).orElse(false);
    }

    public List<CartItemsModel>fetchCartItems(String userId){
        
    return userRepo.findById(Long.valueOf(userId))
        .map(cartItemsRepo :: findByUser)
        .orElseGet(List :: of);
    }

    public void clearCart(String userId){
userRepo.findById(Long.valueOf(userId)).ifPresent(user -> cartItemsRepo.deleteByUser(user) );
    }
}
