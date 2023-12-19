/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author SAMUEL
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private String name;
    private Integer price;
    
}
