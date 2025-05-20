package com.elc1009.projeto3.backend.model;

import java.math.BigDecimal;
import java.util.List;

public class Item {
    
    private BigDecimal value;
    private List<String> payers;

    public BigDecimal getValue() {
        return value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    public List<String> getPayers() {
        return payers;
    }
    
    public void setPayers(List<String> payers) {
        this.payers = payers;
    }
}
