package com.mani.E_commerce.repository.UserRepository;

import com.mani.E_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository


public interface UserRepository  extends JpaRepository<User,Long> {

    Optional<User> findFirstByEmail(String Email);
}
