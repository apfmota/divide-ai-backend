package com.elc1009.projeto3.backend.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elc1009.projeto3.backend.model.Purchase;
import com.elc1009.projeto3.backend.dto.PurchaseDto;
import com.elc1009.projeto3.backend.repository.PurchaseRepository;
import com.elc1009.projeto3.backend.util.ScrapperCaller;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseRepository purchaseRepository;

    @PostMapping
    public String createPurchase(@RequestBody PurchaseDto purchaseDto) {
        System.out.println("URL: " + purchaseDto.getUrl());
        Purchase purchase = new Purchase();
        purchase.setUrl(purchaseDto.getUrl());
        purchaseRepository.save(purchase);
        return ScrapperCaller.call(purchase.getUrl());
    } 

}
