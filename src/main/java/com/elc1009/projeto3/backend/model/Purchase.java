package com.elc1009.projeto3.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Purchase {
    
    private Long id;
    private List<Item> items = new ArrayList<>();
    private User user;
    private List<User> payers = new ArrayList<>();
    private String url;
    private Date scanDate;
    private Date purchaseDate;
    private String storeName;
    private BigDecimal totalValue;

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "purchase")
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany
    @JoinTable(name = "PURCHASE_PAYERS",
        joinColumns = @JoinColumn(name = "PURCHASE_ID"),
        inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    @JsonIgnore
    public List<User> getPayers() {
        return payers;
    }

    public void setPayers(List<User> payers) {
        this.payers = payers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getScanDate() {
        return scanDate;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @JsonProperty("payers")
    @Transient
    public List<String> getPayersUsernames() {
        return payers.stream().map(User::getUsername).toList();
    }
}
