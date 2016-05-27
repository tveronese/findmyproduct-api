package com.findmyproduct.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", uniqueConstraints = @UniqueConstraint(columnNames = { "barcode", "shop_id" }))
public class Product {

    @Id
    public Long id;

    public Long barcode;

    @Column(name = "shop_id")
    public Long shopId;

    public Integer quantity;

}
