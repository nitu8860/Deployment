package com.geekster.Deployment.controller;

import com.geekster.Deployment.dao.ProductRepository;
import com.geekster.Deployment.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepo;
    @PostMapping(value="/post")
    public String saveProduct(@RequestBody Product newProduct) {
        productRepo.save(newProduct);
      return "product saved";
    }
    @GetMapping(value ="/product")
    public List<Product> getList(){
        return productRepo.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return productRepo.findById(id);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productRepo.findById(id);

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setTitle(updatedProduct.getTitle());
            product.setDescription(updatedProduct.getDescription());
            productRepo.save(product);

            return "Product updated";
        } else {
            return "Product not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        Optional<Product> product = productRepo.findById(id);

        if (product.isPresent()) {
            productRepo.delete(product.get());
            return "Product deleted";
        } else {
            return "Product not found";
        }
    }
}