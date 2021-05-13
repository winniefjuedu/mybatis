package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
//VO
public class Product {
	private String id;
	
    private String name;
    
    private Integer price;
    
    private String city;
    
    // getter/setter
	/** Lombok 可透過簡單的注解省略 Java 的 code，
      * 像是 setter、getter、logger…等，目的在消除冗長的 code 和提高開發效率
      * 用@Getter 和@Setter 注解代替
    **/
    
}
