package com.example.springboot.services;

import com.example.springboot.models.OrderModel;
import com.example.springboot.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderModel insert(OrderModel orderModel){
        return orderRepository.save(orderModel);
    }

    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    public OrderModel findById(UUID id){
        Optional<OrderModel> orderOptional = orderRepository.findById(id);
        return orderOptional.get();
    }

    public void delete(UUID id) {
        try {
            orderRepository.deleteById(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }
}