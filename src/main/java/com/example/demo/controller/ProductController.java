package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; //回應實體(HTTP狀態)
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Product;
import com.example.demo.parameter.ProductQueryParameter;
import com.example.demo.service.ProductService;


@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;
	
	@GetMapping
    public ResponseEntity<List<Product>> getProducts(
    		@ModelAttribute ProductQueryParameter param) {
        List<Product> products = productService.getProducts(param);
        return ResponseEntity.ok(products);
    }

	@GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
		Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product request) {
		Product product = productService.createProduct(request);

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
	    Product product = productService.replaceProduct(id, request);
	    return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}