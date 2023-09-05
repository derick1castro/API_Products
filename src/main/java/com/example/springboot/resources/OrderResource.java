package com.example.springboot.resources;

import com.example.springboot.dtos.CategoryRecordDto;
import com.example.springboot.dtos.OrderRecordDto;
import com.example.springboot.models.CategoryModel;
import com.example.springboot.models.OrderModel;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderModel> saveOrder(@RequestBody @Valid OrderRecordDto orderRecordDto) {
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(orderModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrders(){
        List<OrderModel> ordersList = orderService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") UUID id) {
        OrderModel orderOptional = orderService.findById(id);
        if (orderOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") UUID id) {
        OrderModel orderModel = orderService.findById(id);
        if (orderModel == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid OrderRecordDto orderRecordDto) {
        OrderModel orderOptional = orderService.findById(id);
        if (orderOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        OrderModel orderModel = orderOptional;
        BeanUtils.copyProperties(orderRecordDto, orderModel);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, orderModel));
    }
}
