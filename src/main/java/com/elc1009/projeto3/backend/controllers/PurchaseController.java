package com.elc1009.projeto3.backend.controllers;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.dto.ItemJsonDto;
import com.elc1009.projeto3.backend.dto.PurchaseJsonDTo;
import com.elc1009.projeto3.backend.model.Item;
import com.elc1009.projeto3.backend.model.Purchase;
import com.elc1009.projeto3.backend.model.User;
import com.elc1009.projeto3.backend.repository.ItemRepository;
import com.elc1009.projeto3.backend.repository.PurchaseRepository;
import com.elc1009.projeto3.backend.repository.UserRepository;
import com.elc1009.projeto3.backend.response.ErrorResponse;
import com.elc1009.projeto3.backend.response.Response;
import com.elc1009.projeto3.backend.response.SuccessResponse;
import com.elc1009.projeto3.backend.util.ScrapperCaller;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public String createPurchase(@RequestBody Purchase purchase, HttpServletRequest request) {
        try {
            User user = (User) userRepository.findByUsername((String) request.getSession().getAttribute("username"));
            purchase.setUser(user);
            purchase.setScanDate(new Date());
            String nfcData = ScrapperCaller.call(purchase.getUrl());
            ObjectMapper objectMapper = new ObjectMapper();
            PurchaseJsonDTo purchaseJsonDTo = objectMapper.readValue(nfcData, PurchaseJsonDTo.class);

            purchase.setTotalValue(new BigDecimal(purchaseJsonDTo.getTotalValue()));
            purchase.setStoreName(purchaseJsonDTo.getStoreName());
            purchase.setPurchaseDate(purchaseJsonDTo.getDate());
            purchaseRepository.save(purchase);

            for (ItemJsonDto itemJsonDto: purchaseJsonDTo.getItems()) {
                Item item = new Item();
                item.setName(itemJsonDto.getName());
                item.setValue(new BigDecimal(itemJsonDto.getTotalValue()));
                item.setQuantity(new BigDecimal(itemJsonDto.getQuantity()));
                item.setUnit(itemJsonDto.getUnit());
                item.setPurchase(purchase);
                purchase.getItems().add(item);
                itemRepository.save(item);
            }
            purchaseRepository.save(purchase); //sou ateu de cascade

            return """
                {
                    "purchaseId": %d,
                    "nfcData": %s
                }
            """.formatted(purchase.getId() ,nfcData);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResponse("Error while parsing NFC data: " + e.getMessage()).toString();
        }
    } 

    @GetMapping(params = "!id")
    public List<Purchase> getPurchases(HttpServletRequest request) {
        User user = (User) userRepository.findByUsername((String) request.getSession().getAttribute("username"));
        return purchaseRepository.findByUser(user);
    }

    @GetMapping(params = "id")
    public Purchase getPurchase(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    @DeleteMapping
    public Response deletePurchase(@RequestBody Purchase purchaseDto) {
        Purchase purchase = purchaseRepository.findById(purchaseDto.getId()).get();
        purchaseRepository.delete(purchase);
        return new SuccessResponse("purchase deleted successfully");
    }

    @PutMapping
    public Response updatePayers(@RequestBody Purchase purchaseDto) {
        Purchase purchase = purchaseRepository.findById(purchaseDto.getId()).get();
        purchase.setPayers(purchaseDto.getPayers());
        purchaseRepository.save(purchase);
        return new SuccessResponse("purchase updated successfully");
    }
}
