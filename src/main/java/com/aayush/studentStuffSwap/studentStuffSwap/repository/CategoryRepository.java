package com.aayush.studentStuffSwap.studentStuffSwap.repository;

import com.aayush.studentStuffSwap.studentStuffSwap.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
