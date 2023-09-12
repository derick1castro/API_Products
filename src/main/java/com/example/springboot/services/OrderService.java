package com.example.springboot.services;

import com.example.springboot.models.OrderItemModel;
import com.example.springboot.models.Order;
import com.example.springboot.repositories.OrderItemRepository;
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

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order insert(Order order) {
        // Salvar o pedido principal
        Order savedOrder = orderRepository.save(order);

        // Associar os itens ao pedido principal
        List<OrderItemModel> orderItems = order.getItems();
        for (OrderItemModel orderItem : orderItems) {
            orderItem.getId().setOrder(savedOrder);
        }

        // Salvar os itens do pedido usando o repositório dos itens
        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        return orderOptional.get();
    }

    public void delete(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Order update(Long id, Order order) {
        try {
            Order orderOptional = orderRepository.getReferenceById(id);
            updateData(orderOptional, order);
            return orderRepository.save(orderOptional);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void updateData(Order orderOptional, Order order) {
        //orderOptional.setOrderStatus(order.getOrderStatus());
        orderOptional.setClient(order.getClient());
        orderOptional.setMoment(order.getMoment());
    }
}
