package com.elc1009.projeto3.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elc1009.projeto3.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    
    User findByUserName(String userName);
    
    User findByUserNameAndPassword(String userName, String password);
    
    boolean existsByUserName(String userName);
}
