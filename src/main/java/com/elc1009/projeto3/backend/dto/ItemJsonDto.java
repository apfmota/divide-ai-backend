package com.elc1009.projeto3.backend.dto;

public class ItemJsonDto {
    
    private String name;
    private String totalValue;
    private String quantity;
    private String unit;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTotalValue() {
        return totalValue;
    }
    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
