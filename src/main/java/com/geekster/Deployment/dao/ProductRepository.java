package com.geekster.Deployment.dao;

import com.geekster.Deployment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
