/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.converter;

import com.egg.ordermanagement.DTO.ProductDTO;
import com.egg.ordermanagement.entities.Product;
import com.egg.ordermanagement.exceptions.MyException;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author SAMUEL
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    public ProductDTO toDTO(Product product);

    public Product toEntity(ProductDTO DTO);

    public List<ProductDTO> toListDTO (List<Product> productList);


/*    public ProductDTO toDTO(Product product) throws MyException {
        if (product == null || product.getPrice() == null || product.getName() == null) {
            throw new MyException("PurchaseOrder data is null or empty");
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public Product toEntity(ProductDTO ProductDTO) throws MyException {
        if (ProductDTO == null || ProductDTO.getPrice() == null || ProductDTO.getName() == null) {
            throw new MyException("PurchaseOrderDTO data is null or empty");
        }

        Product product = new Product();
        product.setName(ProductDTO.getName());
        product.setPrice(ProductDTO.getPrice());
        return product;
    }

 */
}

