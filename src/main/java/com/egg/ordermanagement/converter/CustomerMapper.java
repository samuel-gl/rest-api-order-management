/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.converter;

import com.egg.ordermanagement.DTO.CustomerDTO;
import com.egg.ordermanagement.DTO.ProductDTO;
import com.egg.ordermanagement.entities.Customer;
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
public interface CustomerMapper {

    public CustomerDTO toDTO(Customer customer);

    public Customer toEntity(CustomerDTO DTO);

    public List<CustomerDTO> toListDTO (List<Customer> customerList);

/*    public CustomerDTO toDTO(Customer customer) throws MyException {
        if (customer == null || customer.getEmail() == null || customer.getName() == null) {
            throw new MyException("PurchaseOrder data is null or empty");
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }

    public Customer toEntity(CustomerDTO CustomerDTO) throws MyException {
        if (CustomerDTO == null || CustomerDTO.getEmail() == null || CustomerDTO.getName() == null) {
            throw new MyException("PurchaseOrderDTO data is null or empty");
        }

        Customer customer = new Customer();
        customer.setName(CustomerDTO.getName());
        customer.setEmail(CustomerDTO.getEmail());
        customer.setPhone(CustomerDTO.getPhone());
        return customer;
    }*/
}
