/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.DTO;

import com.egg.ordermanagement.entities.Customer;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author SAMUEL
 */

@NoArgsConstructor
@Data
public class PurchaseOrderDTO {
    
    private Date date;
    private Integer amount;
    private Customer customer;    

    public PurchaseOrderDTO(Date date, Integer amount, Customer customer) {
        this.date = new Date();
        this.amount = amount;
        this.customer = customer;
    }
    
    
    
}
