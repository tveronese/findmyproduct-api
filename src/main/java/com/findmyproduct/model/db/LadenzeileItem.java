package com.findmyproduct.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ladenzeile_item")
public class LadenzeileItem {

    @Id
    public Long lzId;

    @ManyToOne
    @JoinColumn(name = "barcode", referencedColumnName = "barcode")
    public Product product;

}
