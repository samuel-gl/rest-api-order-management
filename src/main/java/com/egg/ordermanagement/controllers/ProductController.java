/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.controllers;

import com.egg.ordermanagement.DTO.ProductDTO;
import com.egg.ordermanagement.entities.Product;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.services.ProductService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author SAMUEL
 */

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/")
    private ResponseEntity<List<ProductDTO>> getProducts(){
        
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO){
            Product product = productService.createProduct(productDTO);
            return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) throws MyException{

            ProductDTO productDTO = productService.getProduct(id);
            if(productDTO!=null){
                return new ResponseEntity<>(productDTO,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> editProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws MyException{
            productService.editProduct(id, productDTO);
            return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }    
    
    @DeleteMapping("/{id}")
    private ResponseEntity deleteProduct(@PathVariable Long id) throws MyException{
            productService.deleteProduct(id);
            return new ResponseEntity(HttpStatus.OK);
    }    
    
}
