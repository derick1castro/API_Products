package com.example.springboot.services;

import com.example.springboot.models.CategoryModel;
import com.example.springboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //inserir todos os usuários
    public CategoryModel insert(CategoryModel categoryModel){
        return categoryRepository.save(categoryModel);
    }

    //Listar todos os usuários
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    //Listar um único usuário
    public CategoryModel findById(UUID id) {
        Optional<CategoryModel> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.get();
    }

    //Deletar um usuário
    public void delete(UUID id){
        try{
            categoryRepository.deleteById(id);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    //Atualizar um usuário
    public CategoryModel update(UUID id, CategoryModel categoryModel) {
        try {
            CategoryModel categoryOptional = categoryRepository.getReferenceById(id);
            updateData(categoryOptional, categoryModel);
            return categoryRepository.save(categoryOptional);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void updateData(CategoryModel categoryOptional, CategoryModel categoryModel) {
        categoryOptional.setName(categoryModel.getName());
    }
}
