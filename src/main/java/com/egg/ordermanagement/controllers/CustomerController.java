/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.controllers;

import com.egg.ordermanagement.DTO.CustomerDTO;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.services.CustomerService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
/*    @GetMapping("")
    private ResponseEntity<List<CustomerDTO>> getCustomers(){
        List<CustomerDTO> customerList = customerService.getAllCustomer();
        return new ResponseEntity<>(customerList, HttpStatus.CREATED);
    }

 */
    
 /*    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)    
    private void createCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            customerService.createCustomer(customerDTO);
        } catch (MyException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/ //CREAR USUARIO DESDE ENDPOINT../customer


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> userById(@PathVariable Long id){
        try {
            CustomerDTO customer = customerService.getCustomer(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (MyException e) {
            //throw new RuntimeException(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }



    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)    
    public void editCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) throws MyException{
            customerService.editCustomer(id, customerDTO);

    }    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)    
    public void deleteCustomer(@PathVariable Long id) throws MyException{
            customerService.deleteCustomer(id);
    }
    
}