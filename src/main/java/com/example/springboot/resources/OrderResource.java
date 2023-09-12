package com.example.springboot.resources;


import com.example.springboot.dtos.OrderRecordDto;
import com.example.springboot.models.Order;
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
    public ResponseEntity<Order> saveOrder(@RequestBody @Valid OrderRecordDto orderRecordDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRecordDto, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> ordersList = orderService.findAll();
        if (!ordersList.isEmpty()) {
            for (Order order : ordersList) {
                Long id = order.getId();
                order.add(linkTo(methodOn(OrderResource.class).getOneOrder(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ordersList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") Long id) {
        Order orderOptional = orderService.findById(id);
        if (orderOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        orderOptional.add(linkTo(methodOn(OrderResource.class).getAllOrders()).withRel("Products List"));

        return ResponseEntity.status(HttpStatus.OK).body(orderOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long id) {
        Order order = orderService.findById(id);
        if (order == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") Long id,
                                                 @RequestBody @Valid OrderRecordDto orderRecordDto) {
        Order orderOptional = orderService.findById(id);
        if (orderOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        Order order = orderOptional;
        BeanUtils.copyProperties(orderRecordDto, order);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, order));
    }
}
