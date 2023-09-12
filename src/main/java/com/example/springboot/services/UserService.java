package com.example.springboot.services;

import com.example.springboot.models.User;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User insert(User user){
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    public void delete(Long id){
        try{
            userRepository.deleteById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    public User update(Long id, User user) {
        try {
            User userOptional = userRepository.getReferenceById(id);
            updateData(userOptional, user);
            return userRepository.save(userOptional);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void updateData(User userOptional, User user) {
        userOptional.setName(user.getName());
        userOptional.setEmail(user.getEmail());
        userOptional.setPhone(user.getPhone());
        userOptional.setPassword(user.getPassword());
    }
}
