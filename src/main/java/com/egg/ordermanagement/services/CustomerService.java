/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.services;

import com.egg.ordermanagement.DTO.CustomerDTO;
import com.egg.ordermanagement.converter.CustomerMapper;
import com.egg.ordermanagement.entities.Customer;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author SAMUEL
 */

@Service
public class CustomerService implements UserDetailsService {
    
    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    ImageService imageService;

    @Autowired
    CustomerMapper customerMapper;
    
    @Transactional
    public void createCustomer(CustomerDTO customerDTO, MultipartFile uploadedFile) throws MyException{
        DTOvalidation(customerDTO);
        Customer c = customerMapper.toEntity(customerDTO);
        String pw = c.getPassword();
        c.setPassword(new BCryptPasswordEncoder().encode(pw));
        c.setImage(imageService.save(uploadedFile));
        customerRepo.save(c);
    }
    
    @Transactional
    public void editCustomer(Long id, CustomerDTO customerDTO) throws MyException{
        DTOvalidation(customerDTO);
        Customer c = IDvalidation(id);
        c = customerMapper.toEntity(customerDTO);
        c.setId(id);
        customerRepo.save(c);
    }
    
    @Transactional
    public void deleteCustomer(Long id) throws MyException{
        Customer c = IDvalidation(id);
        customerRepo.delete(c);
    }
    
    public List<CustomerDTO> getAllCustomer(){
        List<Customer> customers = new ArrayList();
        List<CustomerDTO> customersDTO = new ArrayList();
        customers = customerRepo.findAll();
        customersDTO = customerMapper.toListDTO(customers);
        
        return customersDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepo.getUserByEmail(email);
        if(customer!= null){
            List<GrantedAuthority> permissions = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + customer.getRole().toString());
            permissions.add(p);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            session.setAttribute("customersession",customer);
            return new User(customer.getEmail(), customer.getPassword(),permissions);
        } else{
            return null;
        }
        }


    public CustomerDTO getCustomer(Long id) throws MyException{
        return customerMapper.toDTO(IDvalidation(id));
    }
    
    private Customer IDvalidation(Long id) throws MyException{
        Optional<Customer> response = customerRepo.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else{
            throw new MyException("El id ingresado no esta asociado a ningun cliente");
        }

    }
    
    private void DTOvalidation(CustomerDTO DTO) throws MyException{
        
        if(DTO.getEmail().equals("")||DTO.getEmail()==null){
            throw new MyException("El email no puede ser vacio o estar nulo");
        }
        if(DTO.getName().equals("")||DTO.getName()==null){
            throw new MyException("El nombre no puede ser vacio o estar nulo");
        }
        if(DTO.getPhone().equals("")||DTO.getPhone()==null){
            throw new MyException("El telefono no puede ser vacio o estar nulo");
        }
    }


}
