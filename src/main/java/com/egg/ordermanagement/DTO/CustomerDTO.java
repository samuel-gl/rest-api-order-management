/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.DTO;

import com.egg.ordermanagement.entities.Image;
import com.egg.ordermanagement.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author SAMUEL
 */

//@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {

    private String name;
    private String email;
    private String phone;
    private String password;
    private Role role;
    //private MultipartFile image;

    public CustomerDTO(){
        this.role=Role.USER;
    }
}

