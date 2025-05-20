package com.elc1009.projeto3.backend.model;

import java.util.List;

public class Purchase {
    
    private List<Item> items;
    private User user;
    private List<String> payers;

    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getPayers() {
        return payers;
    }

    public void setPayers(List<String> payers) {
        this.payers = payers;
    }

    
}
