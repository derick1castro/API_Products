package com.example.springboot.resources;


import com.example.springboot.dtos.OrderRecordDto;
import com.example.springboot.models.OrderModel;
import com.example.springboot.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        List<OrderModel> ordersList = orderService.findAll();
        if (!ordersList.isEmpty()) {
            for (OrderModel order : ordersList) {
                UUID id = order.getIdOrder();
                order.add(linkTo(methodOn(OrderResource.class).getOneOrder(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") UUID id) {
        OrderModel orderOptional = orderService.findById(id);
        if (orderOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        orderOptional.add(linkTo(methodOn(OrderResource.class).getAllOrders()).withRel("Products List"));

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
