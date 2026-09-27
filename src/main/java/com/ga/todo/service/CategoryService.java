package com.ga.todo.service;

import com.ga.todo.exceptions.InformationExistException;
import com.ga.todo.exceptions.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.User;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.security.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    // 1. create
    public Category createCategory(Category categoryObject) {
        System.out.println("Service calling createCategory ==>");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("Category with name " + category.getName() + " already exists");
        }
        categoryObject.setUser(CategoryService.getCurrentLoggedInUser());
        return categoryRepository.save(categoryObject);
    }

    // 2. readaall
    public List<Category> getCategories() {
        System.out.println("Service calling getCategories ==>");
        return categoryRepository.findAll();
    }

    // 3. read one
    public Category getCategory(Long categoryId) {
        System.out.println("Service calling getCategory ==>");
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InformationNotFoundException("Category with id " + categoryId + " not found"));
    }

    // 4. uupdate
    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("Service calling updateCategory ==>");
        Category category = getCategory(categoryId);
        category.setName(categoryObject.getName());
        category.setDescription(categoryObject.getDescription());
        return categoryRepository.save(category);
    }

    // 5. delete
    public Category deleteCategory(Long categoryId) {
        System.out.println("Service calling deleteCategory ==>");
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return category;
    }
}