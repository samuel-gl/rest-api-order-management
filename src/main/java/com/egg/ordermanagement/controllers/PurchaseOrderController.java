/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.controllers;

import com.egg.ordermanagement.DTO.PurchaseOrderDTO;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.services.PurchaseOrderService;
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
@RequestMapping("/purchaseorder")
public class PurchaseOrderController {
 
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    
    /* @GetMapping("/")
    private ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrder(){

        List<PurchaseOrderDTO> orderList = purchaseOrderService.getAllPurchaseOrder();

        return new ResponseEntity<>(orderList,HttpStatus.FOUND);
    }

     */
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)    
    private void createPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO){
        try {
            purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
        } catch (MyException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    private PurchaseOrderDTO editPurchaseOrder(@PathVariable Long id) throws MyException{
        
        return purchaseOrderService.getPurchaseOrder(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)    
    private void editPurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrderDTO purchaseOrderDTO) throws MyException{
            purchaseOrderService.editPurchaseOrder(id, purchaseOrderDTO);

    }    
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)    
    private void deletePurchaseOrder(@PathVariable Long id) throws MyException{
            purchaseOrderService.deletePurchaseOrder(id);
    }    
    
    
    
} 

