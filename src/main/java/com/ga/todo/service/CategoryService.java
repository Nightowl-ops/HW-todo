package com.ga.todo.service;

import com.ga.todo.exceptions.InformationExistException;
import com.ga.todo.exceptions.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category categoryObject) {
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        }
        return categoryRepository.save(categoryObject);
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + categoryId + " not found"));
    }

    public Category updateCategory(Long categoryId, Category categoryObject) {
        Category category = getCategory(categoryId);
        category.setName(categoryObject.getName());
        category.setDescription(categoryObject.getDescription());
        return categoryRepository.save(category);
    }

    public Category deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return category;
    }
}