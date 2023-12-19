package com.egg.ordermanagement.controllers;

import com.egg.ordermanagement.DTO.CustomerDTO;
import com.egg.ordermanagement.entities.Customer;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("")
public class PortalController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<String> mainSite(){
        return new ResponseEntity<>("Esta es la primera pagina",HttpStatus.OK);
    }

    @GetMapping("/register")
    public ResponseEntity<String> createCustomer(){
            return new ResponseEntity<>("Sitio de registro usuarios nuevos",HttpStatus.OK);
    }

    @RequestMapping(path = "/register", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<CustomerDTO> createCustomer(@RequestPart("data") CustomerDTO customerDTO, @RequestPart("image") MultipartFile uploadedFile){
        try {
            customerService.createCustomer(customerDTO,uploadedFile);
            return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        } catch (MyException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @GetMapping("/restricted")
    public ResponseEntity<String> restricted(){
        return new ResponseEntity<>("no tienes paermisos para acceder",HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/index")
    public ResponseEntity<String> index(HttpSession session){

        Customer logged = (Customer) session.getAttribute("customersession");

        if(logged.getRole().toString().equals("ADMIN")){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/admin");
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
            //return (new ResponseEntity<>("Este es el index",headers,HttpStatus.OK));
        }else {
            return new ResponseEntity<>("Este es el index USER", HttpStatus.OK);
        }
    }



}
