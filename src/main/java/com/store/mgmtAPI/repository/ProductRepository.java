package com.store.mgmtAPI.repository;

import com.store.mgmtAPI.repository.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("select prod from Product prod where name like %:productName%")
    List<Product> findProductByName(@Param("productName") String productName);

    @Query("select prod from Product prod where description like %:productDescription%")
    List<Product> findProductByDescription(@Param("productDescription") String productDescription);

    @Transactional
    void deleteByName(String name);

    @Transactional
    @Modifying
    @Query("update Product prod set prod.description= ?2, prod.price = ?3, prod.quantity = ?4 " +
            "where prod.name = ?1")
    void updateProduct(String name, String description, BigDecimal price, int quantity);
}
