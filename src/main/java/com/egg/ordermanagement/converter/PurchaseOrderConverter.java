/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.converter;

import com.egg.ordermanagement.DTO.PurchaseOrderDTO;
import com.egg.ordermanagement.entities.PurchaseOrder;
import com.egg.ordermanagement.exceptions.MyException;
import org.springframework.stereotype.Component;

/**
 *
 * @author SAMUEL
 */

@Component
public class PurchaseOrderConverter {
    
    public PurchaseOrderDTO toDTO(PurchaseOrder order) throws MyException {
        if (order == null || order.getDate() == null || order.getAmount() == null || order.getCustomer() == null) {
            throw new MyException("PurchaseOrder data is null or empty");
        }

        PurchaseOrderDTO orderDTO = new PurchaseOrderDTO();
        orderDTO.setDate(order.getDate());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setCustomer(order.getCustomer());
        return orderDTO;
    }

    public PurchaseOrder toEntity(PurchaseOrderDTO orderDTO) throws MyException {
        if (orderDTO == null || orderDTO.getDate() == null || orderDTO.getAmount() == null) {
            throw new MyException("PurchaseOrderDTO data is null or empty");
        }

        PurchaseOrder order = new PurchaseOrder();
        order.setDate(orderDTO.getDate());
        order.setAmount(orderDTO.getAmount());
        order.setCustomer(orderDTO.getCustomer());
        return order;
    }
}
