package com.ga.todo.service;

import com.ga.todo.exceptions.InformationNotFoundException;
import com.ga.todo.model.Category;
import com.ga.todo.model.Item;
import com.ga.todo.repository.CategoryRepository;
import com.ga.todo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    private Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category with id " + categoryId + " not found")
        );
    }

    // 1. create
    public Item createItem(Long categoryId, Item item) {
        System.out.println("Service calling createItem ==>");
        Category category = findCategory(categoryId);
        item.setCategory(category);
        return itemRepository.save(item);
    }

    // 2. read all
    public List<Item> getItems(Long categoryId) {
        System.out.println("Service calling getItems ==>");
        findCategory(categoryId);
        return itemRepository.findByCategoryId(categoryId);
    }

    // 3. read one
    public Item getItem(Long categoryId, Long itemId) {
        System.out.println("Service calling getItem ==>");
        findCategory(categoryId);
        return itemRepository.findByIdAndCategoryId(itemId, categoryId)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Item with id " + itemId + " not found in category " + categoryId));
    }

    // 4. delete
    public Item deleteItem(Long categoryId, Long itemId) {
        System.out.println("Service calling deleteItem ==>");
        findCategory(categoryId);
        Item item = itemRepository.findByIdAndCategoryId(itemId, categoryId)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Item with id " + itemId + " not found in category " + categoryId));

        itemRepository.delete(item);
        return item;
    }

    // 5. update
    public Item updateItem(Long categoryId, Long itemId, Item itemObject) {
        System.out.println("Service calling updateItem ==>");
        findCategory(categoryId);
        Item existingItem = itemRepository.findByIdAndCategoryId(itemId, categoryId)
                .orElseThrow(() -> new InformationNotFoundException(
                        "Item with id " + itemId + " not found in category " + categoryId));

        existingItem.setName(itemObject.getName());
        existingItem.setDescription(itemObject.getDescription());
        existingItem.setDueDate(itemObject.getDueDate());
        return itemRepository.save(existingItem);
    }
}