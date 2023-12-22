package com.aayush.studentStuffSwap.studentStuffSwap.repository;

import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByScholarNumber(String scholarNumber);
}
