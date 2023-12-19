package com.egg.ordermanagement.converter;

import com.egg.ordermanagement.DTO.CustomerDTO;
import com.egg.ordermanagement.DTO.UserProfileDTO;
import com.egg.ordermanagement.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProfileMapper {
    public UserProfileDTO toDTO(Customer customer);

    public Customer toEntity(UserProfileDTO DTO);

    public List<UserProfileDTO> toListDTO (List<Customer> customerList);
}
