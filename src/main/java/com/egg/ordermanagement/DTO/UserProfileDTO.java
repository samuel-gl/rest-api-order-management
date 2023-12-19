package com.egg.ordermanagement.DTO;

import com.egg.ordermanagement.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDTO {
    private String name;
    private String email;
    private String phone;

    private Image image;

}
