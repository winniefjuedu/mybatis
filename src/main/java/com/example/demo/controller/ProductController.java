package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity; //回應實體(HTTP狀態)
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductBuilder;
import com.example.demo.parameter.ProductQueryParameter;
import com.example.demo.service.ProductService;


@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/products")
public class ProductController {
	
	//相當於 @Autowired ProductService productService，可驗證是否有Autowired dead lock
	private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
	
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

	@GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.getProductByPK(id));
    }
	
	@PostMapping("/")
	public ResponseEntity<Product> insert() {
		Product product = Product.builder()
				.id(UUID.randomUUID().toString())
				.name("test")
				.price(230)
				.city("Tokyo")
				.build();
		productService.create(product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@PutMapping("/{id}") //「覆蓋」掉特定的資源
	public ResponseEntity<Product> update(@PathVariable("id") String id){
	    Product product = Product.builder()
	    		.id(id)
	    		.name("test_update")
	    		.price(300)
	    		.city("Taipei")
	    		.build();
	    productService.update(product);
	    product = productService.getProductByPK(id);
	    return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable("id") String id) {
		Product product = productService.getProductByPK(id);
		productService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
//	@DeleteMapping("/")
//	public ResponseEntity<String> deleteAll() {
//		return ResponseEntity.status(HttpStatus.OK).body(productService.deleteAll() > 0 ? "all deleted" : "fail");
//	}
	
}