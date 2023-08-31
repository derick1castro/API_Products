package com.example.springboot.services;

import com.example.springboot.models.ProductModel;
import com.example.springboot.models.UserModel;
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

    //inserir todos os usuários
    public UserModel insert(UserModel userModel){
        return userRepository.save(userModel);
    }

    //Listar todos os usuários
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    //Listar um único usuário
    public UserModel findById(UUID id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    //Deletar um usuário
    public void delete(UUID id){
        try{
            userRepository.deleteById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }
}
