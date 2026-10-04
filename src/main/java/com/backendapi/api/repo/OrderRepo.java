package com.backendapi.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backendapi.api.model.OrderModel;
@Repository 
public interface OrderRepo extends  JpaRepository<OrderModel,Long>{

}
