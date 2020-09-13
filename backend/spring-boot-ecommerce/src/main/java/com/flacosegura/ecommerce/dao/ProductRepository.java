package com.flacosegura.ecommerce.dao;

import com.flacosegura.ecommerce.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}