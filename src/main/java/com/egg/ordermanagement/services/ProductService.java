/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.services;

import com.egg.ordermanagement.DTO.ProductDTO;
import com.egg.ordermanagement.converter.ProductMapper;
import com.egg.ordermanagement.entities.Product;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SAMUEL
 */

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired(required=true)
    ProductMapper productMapper;
    
    @Transactional
    public Product createProduct(ProductDTO productDTO){
        try {
            DTOvalidation(productDTO);
            Product p = productMapper.toEntity(productDTO);
            return productRepository.save(p);
        } catch (MyException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Transactional
    public void editProduct(Long id, ProductDTO productDTO) throws MyException{
        DTOvalidation(productDTO);
        Product p = IDvalidation(id);
        p.setName(productDTO.getName());
        p.setPrice(productDTO.getPrice());
        p.setId(id);
        productRepository.save(p);
    }
    
    @Transactional
    public void deleteProduct(Long id) throws MyException{
        Product p = IDvalidation(id);
        productRepository.delete(p);
    }
    
    public List<ProductDTO> getAllProduct(){
        List<ProductDTO> productsDTO = new ArrayList();
        productsDTO = productMapper.toListDTO(productRepository.findAll());
        return productsDTO;
    }
    
    public ProductDTO getProduct(Long id) throws MyException{
        try{
            return productMapper.toDTO(IDvalidation(id));
        }
        catch (Exception ex) {
            return null;
        }

    }
    
    private Product IDvalidation(Long id) throws MyException{
        Optional<Product> response = productRepository.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else{
            throw new MyException("El id ingresado no esta asociado a ningun producto");
        }

    }
    
    private void DTOvalidation(ProductDTO DTO) throws MyException{
        
        if(DTO.getName().equals("")||DTO.getName()==null){
            throw new MyException("El nombre del producto no puede ser vacio o estar nulo");
        }
        if(DTO.getPrice()==null){
            throw new MyException("El precio no puede ser vacio o estar nulo");
        }
    }
    
}
