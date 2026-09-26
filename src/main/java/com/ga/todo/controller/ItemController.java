package com.ga.todo.controller;

import com.ga.todo.model.Item;
import com.ga.todo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    @PostMapping("/categories/{categoryId}/items")
    public Item createItem(@PathVariable(value = "categoryId") Long categoryId,
                           @RequestBody Item itemObject) {
        System.out.println("calling createItem ==>");
        return itemService.createItem(categoryId, itemObject);
    }

    @GetMapping("/categories/{categoryId}/items")
    public List<Item> getItems(@PathVariable(value = "categoryId") Long categoryId) {
        System.out.println("calling getItems ==>");
        return itemService.getItems(categoryId);
    }

    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(@PathVariable(value = "categoryId") Long categoryId,
                        @PathVariable(value = "itemId") Long itemId) {
        System.out.println("calling getItem ==>");
        return itemService.getItem(categoryId, itemId);
    }

    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable(value = "categoryId") Long categoryId,
                           @PathVariable(value = "itemId") Long itemId,
                           @RequestBody Item itemObject) {
        System.out.println("calling updateItem ==>");
        return itemService.updateItem(categoryId, itemId, itemObject);
    }

    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public Item deleteItem(@PathVariable(value = "categoryId") Long categoryId,
                           @PathVariable(value = "itemId") Long itemId) {
        System.out.println("calling deleteItem ==>");
        return itemService.deleteItem(categoryId, itemId);
    }
}