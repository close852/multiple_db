package com.cjhm.multiple.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjhm.multiple.product.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
