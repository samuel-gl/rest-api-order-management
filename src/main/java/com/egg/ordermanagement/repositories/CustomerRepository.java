/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.repositories;

import com.egg.ordermanagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAMUEL
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    public Customer getUserByEmail(@Param("email")String email);


}
