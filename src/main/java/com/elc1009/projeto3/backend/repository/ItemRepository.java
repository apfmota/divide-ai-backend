package com.elc1009.projeto3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elc1009.projeto3.backend.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
