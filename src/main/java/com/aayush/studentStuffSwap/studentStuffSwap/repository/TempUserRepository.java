package com.aayush.studentStuffSwap.studentStuffSwap.repository;

import com.aayush.studentStuffSwap.studentStuffSwap.model.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempUserRepository extends JpaRepository<TempUser, Long> {
    Optional<TempUser> findByEmail(String username);
}
