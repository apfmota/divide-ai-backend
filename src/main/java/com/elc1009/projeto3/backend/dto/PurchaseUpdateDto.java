package com.elc1009.projeto3.backend.dto;

import java.util.List;

public class PurchaseUpdateDto {

    
    private Long id;
    private List<String> payers;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<String> getPayers() {
        return payers;
    }
    public void setPayers(List<String> payers) {
        this.payers = payers;
    }
}
