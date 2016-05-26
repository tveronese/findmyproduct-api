package com.findmyproduct.model;

import static org.springframework.data.mongodb.core.index.GeoSpatialIndexType.GEO_2DSPHERE;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Shop {

    @Id
    public String id;

    public String name;

    @GeoSpatialIndexed(type = GEO_2DSPHERE)
    public Point location;

    public String address;

}
