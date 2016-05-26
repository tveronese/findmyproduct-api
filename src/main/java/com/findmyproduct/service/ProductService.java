package com.findmyproduct.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.findmyproduct.api.ProductShop;
import com.findmyproduct.model.ProductStatus;
import com.findmyproduct.model.Shop;
import com.findmyproduct.repository.ShopRepository;

@Service
public class ProductService {

    private final ShopRepository shopRepository;

    @Autowired
    public ProductService(final ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ProductStatus findStatusByLzId(final Long lzId) {
        return ProductStatus.AVAILABLE;
    }

    public List<ProductShop> findProductWithNearestShops(final Long lzId, final Point location,
            final Distance distance) {
        final List<Shop> shops = this.shopRepository.findByLocationNear(location, distance);
        return shops.stream().map(this::toResource).collect(Collectors.toList());
    }

    private ProductShop toResource(final Shop shop) {
        final ProductShop productShop = new ProductShop();
        productShop.name = shop.name;
        productShop.latitude = shop.location.getY();
        productShop.longitude = shop.location.getX();
        productShop.address = shop.address;
        return productShop;
    }

}
