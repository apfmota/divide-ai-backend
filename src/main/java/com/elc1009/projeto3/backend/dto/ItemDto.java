package com.elc1009.projeto3.backend.dto;

import java.util.List;

public class ItemDto {
    
    private Long id;
    private Long purchaseId;
    private List<String> payers;
    private String value;
    private String name;
    private String quantity;

    public List<String> getPayers() {
        return payers;
    }

    public void setPayers(List<String> payers) {
        this.payers = payers;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
