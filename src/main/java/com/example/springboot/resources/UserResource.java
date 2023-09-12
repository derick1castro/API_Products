package com.example.springboot.resources;

import com.example.springboot.dtos.UserRecordDto;
import com.example.springboot.models.User;
import com.example.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        User user = new User();
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insert(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> usersList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") Long id) {
        User userOptional = userService.findById(id);
        if (userOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        //productOptional.add(linkTo(methodOn(ProductResource.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(userOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
        User userOptional = userService.findById(id);
        if(userOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid UserRecordDto userRecordDto) {
        User userOptional = userService.findById(id);
        if (userOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        User user = userOptional;
        BeanUtils.copyProperties(userRecordDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user));
    }
}
