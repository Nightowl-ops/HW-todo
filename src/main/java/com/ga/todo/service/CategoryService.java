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

    // 1. Create
    public Category createCategory(Category categoryObject) {
        System.out.println("Service calling createCategory ==>");
        User user = getCurrentLoggedInUser();
        Category category = categoryRepository.findByUserIdAndName(user.getId(), categoryObject.getName());
        if (category != null) {
            throw new InformationExistException("Category with name " + category.getName() + " already exists");
        }
        categoryObject.setUser(user);
        return categoryRepository.save(categoryObject);
    }

    // 2. Read all
    public List<Category> getCategories() {
        System.out.println("Service calling getCategories ==>");
        User user = getCurrentLoggedInUser();
        return categoryRepository.findByUserId(user.getId());
    }

    // 3. Read one
    public Category getCategory(Long categoryId) {
        System.out.println("Service calling getCategory ==>");
        User user = getCurrentLoggedInUser();
        Category category = categoryRepository.findByIdAndUserId(categoryId, user.getId());
        if (category == null) {
            throw new InformationNotFoundException("Category with id " + categoryId + " not found");
        }
        return category;
    }
    // 4. Update
    public Category updateCategory(Long categoryId, Category categoryObject) {
        System.out.println("Service calling updateCategory ==>");
        Category category = getCategory(categoryId);


        if (!category.getName().equalsIgnoreCase(categoryObject.getName()) &&
                categoryRepository.findByUserIdAndName(getCurrentLoggedInUser().getId(), categoryObject.getName()) != null) {
            throw new InformationExistException("Category with name " + categoryObject.getName() + " already exists");
        }

        category.setName(categoryObject.getName());
        category.setDescription(categoryObject.getDescription());
        return categoryRepository.save(category);
    }

    // 5. Delete
    public Category deleteCategory(Long categoryId) {
        System.out.println("Service calling deleteCategory ==>");
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return category;
    }
}