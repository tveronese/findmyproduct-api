package com.findmyproduct.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.findmyproduct.model.ProductStatus;
import com.findmyproduct.service.ProductService;

@Controller
@EnableAutoConfiguration
@RequestMapping("/products")
public class ProductController {

    private static final double DEFAULT_DISTANCE = 5D;

    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @RequestMapping("{lzId}")
    public ProductStatus status(final Long lzId) {
        return this.productService.findStatusByLzId(lzId);
    }

    @ResponseBody
    @RequestMapping("{lzId}/shops")
    public List<ProductShop> listShops(final Long lzId, @RequestParam("location") final Point location,
            @RequestParam("distance") final Optional<Double> distance) {
        final Distance dist = new Distance(distance.orElse(DEFAULT_DISTANCE), Metrics.KILOMETERS);
        return this.productService.findProductWithNearestShops(lzId, location, dist);
    }

}
