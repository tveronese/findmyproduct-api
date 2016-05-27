package com.findmyproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findmyproduct.model.db.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT l.product FROM LadenzeileItem l WHERE l.lzId = :lzId")
    List<Product> findByLzId(@Param("lzId") Long lzId);

    @Query("SELECT l.product FROM LadenzeileItem l WHERE l.lzId = :lzId AND l.product.shopId = :shopId")
    Product findByLzIdAndShopId(@Param("lzId") Long lzId, @Param("shopId") Long shopId);

}
