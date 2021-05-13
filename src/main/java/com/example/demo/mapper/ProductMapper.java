package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.demo.entity.Product;
import com.example.demo.entity.Product.ProductBuilder;

@Mapper
public interface ProductMapper {

	@Select("SELECT * FROM USER ;")
	@Results(id = "user", value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "price", column = "PRICE"),
            @Result(property = "city", column = "CITY")
    })
    List<Product> getAll();
	
	@ResultMap("user")
	@Select("SELECT * FROM USER WHERE ID = #{id}")
	Product getByPrimaryKey(@Param("id") String id);
	
	@Insert("INSERT INTO USER (ID, NAME, PRICE, CITY) " +
			"VALUES (#{id}, #{name}, #{price}, #{city})")
	int insert(Product product);
	
	@Update("UPDATE USER SET NAME= #{name}, PRICE=#{price}, CITY = #{city} WHERE ID = #{id}")
    int update(Product product);

    @Delete("DELETE FROM USER WHERE ID= #{id}")
    int deleteUserById(String id);

    @Delete("DELETE FROM USER")
    int deleteAll();
}
