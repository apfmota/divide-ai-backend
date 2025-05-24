package com.elc1009.projeto3.backend.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseJsonDTo {
    
    private String storeName;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date date;
    private String totalValue;
    private List<ItemJsonDto> items;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public List<ItemJsonDto> getItems() {
        return items;
    }

    public void setItems(List<ItemJsonDto> items) {
        this.items = items;
    }   
   
}
