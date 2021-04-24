package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; //回應實體(HTTP狀態)
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Product;

@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/products")
public class ProductController {
	
	private final List<Product> productDB = new ArrayList<>();
	
	@PostConstruct
    private void initDB() {
        productDB.add(new Product("B0001", "Android Development (Java)", 380));
        productDB.add(new Product("B0002", "Android Development (Kotlin)", 420));
        productDB.add(new Product("B0003", "Data Structure (Java)", 250));
        productDB.add(new Product("B0004", "Finance Management", 450));
        productDB.add(new Product("B0005", "Human Resource Management", 330));
    }

	@GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
		Optional<Product> productOp = productDB.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (!productOp.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Product product = productOp.get();
        return ResponseEntity.ok().body(product);
    }
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody Product request) {
	    boolean isIdDuplicated = productDB.stream()
	            .anyMatch(p -> p.getId().equals(request.getId()));
	    if (isIdDuplicated) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build();
	    }

	    Product product = new Product();
	    product.setId(request.getId());
	    product.setName(request.getName());
	    product.setPrice(request.getPrice());
	    productDB.add(product);

	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest()
	            .path("/{id}")
	            .buildAndExpand(product.getId())
	            .toUri();

	    return ResponseEntity.created(location).body(product);
	}
	
	@PutMapping("/{id}") //「覆蓋」掉特定的資源
	public ResponseEntity<Product> replaceProduct(
	        @PathVariable("id") String id, @RequestBody Product request) {
	    Optional<Product> productOp = productDB.stream()
	            .filter(p -> p.getId().equals(id))
	            .findFirst();

	    if (!productOp.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Product product = productOp.get();
	    product.setName(request.getName());
	    product.setPrice(request.getPrice());

	    return ResponseEntity.ok().body(product);
	}
}