CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `barcode` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_barcode_shop_id` (`barcode`,`shop_id`)
) ENGINE=InnoDB;

CREATE TABLE `ladenzeile_item` (
  `lz_id` bigint(20) NOT NULL,
  `barcode` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`lz_id`),
  KEY `FK_barcode` (`barcode`),
  CONSTRAINT `FK_lz_item_product_barcode` FOREIGN KEY (`barcode`) REFERENCES `product` (`barcode`)
) ENGINE=InnoDB;

INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('1', '0', '1');
INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('1', '2', '2');
INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('1', '3', '6');
INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('1', '7', '3');
INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('2', '3', '1');
INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('2', '0', '4');
INSERT INTO `product` (`barcode`, `quantity`, `shop_id`) VALUES ('2', '10', '8');
INSERT INTO `ladenzeile_item` (`lz_id`, `barcode`) VALUES ('519795155', '1');
INSERT INTO `ladenzeile_item` (`lz_id`, `barcode`) VALUES ('442336810', '2');

select * from ladenzeile_item;
select * from product;