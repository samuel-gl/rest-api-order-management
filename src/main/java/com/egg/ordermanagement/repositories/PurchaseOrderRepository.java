/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.repositories;

import com.egg.ordermanagement.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAMUEL
 */

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
    
}
