package com.findmyproduct.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.findmyproduct.model.mongodb.ProductStatus;
import com.findmyproduct.service.ProductService;

@CrossOrigin
@RestController
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
    @RequestMapping("/{lzId}")
    public ProductStatus status(@PathVariable final Long lzId) {
        return this.productService.findStatusByLzId(lzId);
    }

    @ResponseBody
    @RequestMapping("/{lzId}/shops")
    public List<ProductShop> listShops(@PathVariable final Long lzId, @RequestParam final Point location,
            @RequestParam final Optional<Double> distance) {
        final Distance dist = new Distance(distance.orElse(DEFAULT_DISTANCE), Metrics.KILOMETERS);
        return this.productService.findProductWithNearestShops(lzId, location, dist);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{lzId}/shops/{shopId}/quantity/{quantity}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStock(@PathVariable final Long lzId, @PathVariable final Long shopId,
            @PathVariable final Integer quantity) {
        this.productService.updateStock(lzId, shopId, quantity);
    }

}
