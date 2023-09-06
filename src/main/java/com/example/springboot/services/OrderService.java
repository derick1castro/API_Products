package com.example.springboot.services;

import com.example.springboot.models.OrderItemModel;
import com.example.springboot.models.OrderModel;
import com.example.springboot.models.ProductModel;
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

    public OrderModel insert(OrderModel orderModel) {
        // Salvar o pedido principal
        OrderModel savedOrder = orderRepository.save(orderModel);

        // Associar os itens ao pedido principal
        List<OrderItemModel> orderItems = orderModel.getItems();
        for (OrderItemModel orderItem : orderItems) {
            orderItem.getId().setOrderModel(savedOrder);
        }

        // Salvar os itens do pedido usando o repositório dos itens
        orderItemRepository.saveAll(orderItems);

        return savedOrder;
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

    public OrderModel update(UUID id, OrderModel orderModel) {
        try {
            OrderModel orderOptional = orderRepository.getReferenceById(id);
            updateData(orderOptional, orderModel);
            return orderRepository.save(orderOptional);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void updateData(OrderModel orderOptional, OrderModel orderModel) {
        orderOptional.setOrderStatus(orderModel.getOrderStatus());
        orderOptional.setClient(orderModel.getClient());
        orderOptional.setMoment(orderModel.getMoment());
    }
}
