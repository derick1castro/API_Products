package com.example.springboot.resources;

import com.example.springboot.dtos.CategoryRecordDto;
import com.example.springboot.models.CategoryModel;
import com.example.springboot.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryModel> saveCategory(@RequestBody @Valid CategoryRecordDto categoryRecordDto){
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryRecordDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.insert(categoryModel));
    }

    @GetMapping
    public ResponseEntity<List<CategoryModel>> getAllCategories(){
        List<CategoryModel> categorysList = categoryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categorysList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCategory(@PathVariable(value="id") UUID id) {
        CategoryModel categoryOptional = categoryService.findById(id);
        if (categoryOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        //productOptional.add(linkTo(methodOn(ProductResource.class).getAllProducts()).withRel("Products List"));
        return ResponseEntity.status(HttpStatus.OK).body(categoryOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
        CategoryModel categoryOptional = categoryService.findById(id);
        if(categoryOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid CategoryRecordDto categoryRecordDto) {
        CategoryModel categoryOptional = categoryService.findById(id);
        if (categoryOptional == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        CategoryModel categoryModel = categoryOptional;
        BeanUtils.copyProperties(categoryRecordDto, categoryModel);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, categoryModel));
    }
}
