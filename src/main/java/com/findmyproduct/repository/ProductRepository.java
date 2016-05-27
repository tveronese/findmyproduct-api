package com.findmyproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.findmyproduct.model.db.LadenzeileItem;
import com.findmyproduct.model.db.Product;

public interface ProductRepository extends JpaRepository<LadenzeileItem, Long> {

    @Query("SELECT l.product FROM LadenzeileItem l WHERE l.lzId = :lzId")
    List<Product> findProductsByLzId(@Param("lzId") Long lzId);

}
