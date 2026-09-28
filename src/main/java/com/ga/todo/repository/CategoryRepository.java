package com.ga.todo.repository;

import com.ga.todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);
    Category findByNameAndDescription(String name, String desc);
    Category findByUserIdAndName(Long userId, String categoryName);
    Category findByIdAndUserId(Long categoryId, Long userId);
    List<Category> findByUserId(Long userId);
}