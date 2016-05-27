CREATE TABLE `ladenzeile_item` (
  `lz_id` bigint(20) NOT NULL,
  `barcode` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`lz_id`),
  KEY `FK_barcode` (`barcode`),
  CONSTRAINT `FK_lz_item_product_barcode` FOREIGN KEY (`barcode`) REFERENCES `product` (`barcode`)
) ENGINE=InnoDB;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `barcode` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_barcode_shop_id` (`barcode`,`shop_id`)
) ENGINE=InnoDB;
