package com.findmyproduct.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.findmyproduct.model.Shop;

@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {

    List<Shop> findByLocationNear(Point location, Distance distance);

}
