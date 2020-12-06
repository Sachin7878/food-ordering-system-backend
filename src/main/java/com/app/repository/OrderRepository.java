package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
