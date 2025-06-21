package com.elc1009.projeto3.backend.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Item {
    
    private Long id;
    private BigDecimal value;
    private List<User> payers = new ArrayList<>();
    private Purchase purchase;
    private String name;
    private BigDecimal quantity;
    private String unit;
    private String category;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(precision = 10, scale = 2, name = "VALUE_")
    public BigDecimal getValue() {
        return value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    public List<User> getPayers() {
        return payers;
    }
    
    public void setPayers(List<User> payers) {
        this.payers = payers;
    }

    @ManyToOne
    @JsonIgnore
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("payers")
    @Transient
    public List<String> getPayersUsernames() {
        return payers.stream().map(User::getUsername).toList();
    }
}
