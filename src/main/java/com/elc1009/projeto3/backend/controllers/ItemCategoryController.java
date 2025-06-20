package com.elc1009.projeto3.backend.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.model.Item;
import com.elc1009.projeto3.backend.repository.ItemRepository;
import com.elc1009.projeto3.backend.response.SuccessResponse;

@RestController
@RequestMapping("/item-category")
public class ItemCategoryController {
    
    @Autowired
    ItemRepository itemRepository;

    @PostMapping
    public Object setItemCategory(@RequestBody Map<String, String> body) {
        Item item = itemRepository.findById(Long.parseLong(body.get("itemId"))).orElse(null);
        item.setCategory(body.get("category"));
        itemRepository.save(item);
        return new SuccessResponse("Item category updated successfully");
    }
}
