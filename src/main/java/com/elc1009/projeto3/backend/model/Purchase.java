package com.elc1009.projeto3.backend.model;

import java.util.List;
import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Purchase {
    
    private Long id;
    private List<Item> items;
    private User user;
    private List<String> payers;
    private String code;

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
    
    @ManyToAny
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
