package com.ga.todo.service;

import com.ga.todo.exceptions.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    // 1. create
    public Item createItem(Long categoryId, Item item) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category with id " + categoryId + " not found")
        );
        item.setCategory(category);
        return itemRepository.save(item);
    }

    // 2.readall
    public List<Item> getItems(Long categoryId) {
        System.out.println("service calling getItems ==>");
        return itemRepository.findByCategoryId(categoryId);
    }

    // 3. readone
    public Item getItem(Long categoryId, Long itemId) {
        System.out.println("service calling getItem ==>");
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElse(null);
    }

    // 4. delete
    public Item deleteItem(Long categoryId, Long itemId) {
        System.out.println("service calling deleteItem ==>");
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            itemRepository.deleteById(itemId);
            return item.get();
        }
        return null;
    }

    // 5. update
    public Item updateItem(Long categoryId, Long itemId, Item itemObject) {
        System.out.println("service calling updateItem ==>");
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            Item existingItem = item.get();
            existingItem.setName(itemObject.getName());
            existingItem.setDescription(itemObject.getDescription());
            existingItem.setDueDate(itemObject.getDueDate());
            return itemRepository.save(existingItem);
        }
        return null;
    }
}