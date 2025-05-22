package com.elc1009.projeto3.backend.model;

import java.util.List;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;

@Entity
public class Purchase {
    
    private Long id;
    private List<Item> items;
    private User user;
    private List<String> payers;
    private String url;
    private Date scanDate;
    private Date purchaseDate;
    private String storeName;

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
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @ElementCollection
    public List<String> getPayers() {
        return payers;
    }

    public void setPayers(List<String> payers) {
        this.payers = payers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getScanDate() {
        return scanDate;
    }

    public void setScanDate(Date scanDate) {
        this.scanDate = scanDate;
    }

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

    
}
