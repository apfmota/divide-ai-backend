package com.elc1009.projeto3.backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "USER_") // "user" is a reserved word
public class User {

    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private List<Purchase> purchases = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Purchase> payingPurchases = new ArrayList<>();

    @Id
    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany
    @JoinTable(
        name = "USER_ITEMS",
        joinColumns = @JoinColumn(name = "ITEM_ID"), 
        inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @ManyToMany(mappedBy = "payers")
    public List<Purchase> getPayingPurchases() {
        return payingPurchases;
    }

    public void setPayingPurchases(List<Purchase> payingPurchases) {
        this.payingPurchases = payingPurchases;
    }   
}
