/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement;

import com.egg.ordermanagement.DTO.ProductDTO;
import com.egg.ordermanagement.entities.Product;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.repositories.ProductRepository;
import com.egg.ordermanagement.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author SAMUEL
 */

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    public ProductService productService;
    @MockBean
    public ProductRepository productRepository;

    private ProductDTO createdDTO;

    @BeforeEach
    public void createDTO(){
        createdDTO = new ProductDTO("producto prueba",10000);
    }


/*    @Test
    public void shouldCreateProduct() {

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> {
                    Product product = invocation.getArgument(0);
                    product.setId(1L);
                    return product;
                    }
                );


        Product createdProduct = productService.createProduct(createdDTO);

        Assertions.assertNotNull(createdProduct.getId());
        assertEquals("producto prueba", createdProduct.getName());
        assertEquals(10000L, createdProduct.getPrice().longValue());
    }

    @Test
    public void shouldGetProductById() throws MyException {
        Long productID = 2L;
        Product returnedProduct = new Product(productID,"producto prueba",10000);
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.of(returnedProduct));

        ProductDTO returnedDTO = productService.getProduct(2L);
        assertEquals(returnedDTO,createdDTO);

    }

    @Test
    public void shouldGetProductList(){
        List<Product> returnedList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Product p = new Product((long) i, "producto prueba " + i, i);
            returnedList.add(p);
        }
        when(productRepository.findAll()).thenReturn(returnedList);

        assertEquals(returnedList.size(),2);

    }*/
}
