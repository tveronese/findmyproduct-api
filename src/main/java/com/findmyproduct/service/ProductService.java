package com.findmyproduct.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.findmyproduct.api.ProductShop;
import com.findmyproduct.model.db.Product;
import com.findmyproduct.model.mongodb.ProductStatus;
import com.findmyproduct.model.mongodb.Shop;
import com.findmyproduct.repository.ProductRepository;
import com.findmyproduct.repository.ShopRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository, final ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
    }

    public ProductStatus findStatusByLzId(final Long lzId) {
        final List<Product> products = this.productRepository.findProductsByLzId(lzId);
        return products.isEmpty() ? ProductStatus.NOT_AVAILABLE : ProductStatus.AVAILABLE;
    }

    public List<ProductShop> findProductWithNearestShops(final Long lzId, final Point location,
            final Distance distance) {
        final List<Product> products = this.productRepository.findProductsByLzId(lzId);
        final Map<Long, Integer> lzShopIdsToQty = products.stream()
                .collect(Collectors.toMap(p -> p.shopId, p -> p.quantity));
        final List<Shop> shops = this.shopRepository.findByLzShopIdInAndLocationNear(lzShopIdsToQty.keySet(), location,
                distance);
        return shops.stream().map(shop -> this.toResource(shop, lzShopIdsToQty)).collect(Collectors.toList());
    }

    private ProductShop toResource(final Shop shop, final Map<Long, Integer> lzShopIdsToQty) {
        final ProductShop productShop = new ProductShop();
        productShop.name = shop.name;
        productShop.latitude = shop.location.getY();
        productShop.longitude = shop.location.getX();
        productShop.address = shop.address;
        productShop.quantity = lzShopIdsToQty.get(shop.lzShopId);
        return productShop;
    }

}
