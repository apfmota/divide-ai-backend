package com.elc1009.projeto3.backend.model;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Item {
    
    private Long id;
    private BigDecimal value;
    private List<String> payers;
    private Purchase purchase;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    @ElementCollection
    public List<String> getPayers() {
        return payers;
    }
    
    public void setPayers(List<String> payers) {
        this.payers = payers;
    }

    @ManyToOne
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
