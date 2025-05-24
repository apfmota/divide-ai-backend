package com.elc1009.projeto3.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.dto.ItemDto;
import com.elc1009.projeto3.backend.model.Item;
import com.elc1009.projeto3.backend.model.Purchase;
import com.elc1009.projeto3.backend.repository.ItemRepository;
import com.elc1009.projeto3.backend.repository.PurchaseRepository;
import com.elc1009.projeto3.backend.response.Response;
import com.elc1009.projeto3.backend.response.SuccessResponse;

import java.math.BigDecimal;

@RestController
@RequestMapping("/item")
class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @PutMapping
    public Response updatePayers(@RequestBody ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId()).orElse(null);
        item.setPayers(itemDto.getPayers());
        itemRepository.save(item);
        return new SuccessResponse("Item updated successfully");
    }

    @PostMapping
    public Response splitItem(@RequestBody ItemDto itemDto) {
        Item item = itemRepository.findById(itemDto.getId()).orElse(null);
        Purchase purchase = item.getPurchase();
        Integer originalQuantity = item.getQuantity().intValue();
        item.setQuantity(BigDecimal.ONE);
        item.setValue(item.getValue().divide(new BigDecimal(originalQuantity)));
        for (int i = 1; i < originalQuantity; i++) {
            Item newItem = new Item();
            newItem.setName(item.getName());
            newItem.setValue(item.getValue());
            newItem.setQuantity(BigDecimal.ONE);
            newItem.setUnit(item.getUnit());
            newItem.setPayers(item.getPayers());
            newItem.setValue(item.getValue());
            itemRepository.save(newItem);
            purchase.getItems().add(newItem);
        }
        purchaseRepository.save(purchase);
        return new SuccessResponse("Item split successfully");
    }
}