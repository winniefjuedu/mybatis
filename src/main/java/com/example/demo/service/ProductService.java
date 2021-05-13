package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductBuilder;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.parameter.ProductQueryParameter;
import com.example.demo.repository.ProductRepository;

//業務邏輯層
@Service
@Transactional
public class ProductService {
    
    private final ProductMapper productMapper;

    public ProductService(ProductMapper productMapper){
        this.productMapper = productMapper;
    }
    
    public List<Product> getAll(){
        return productMapper.getAll();
    }

    public Product getProductByPK(String id){
        return productMapper.getByPrimaryKey(id);
    }
    
    public int create(Product product) {
    	return productMapper.insert(product);
    }
    
    public int update(Product product) {
    	return productMapper.update(product);
    }
    
    public int delete(String id) {
    	return productMapper.deleteUserById(id);
    }
    
    public int deleteAll() {
    	return productMapper.deleteAll();
    }

}
