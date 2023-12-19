package com.egg.ordermanagement.controllers;


import com.egg.ordermanagement.DTO.CustomerDTO;
import com.egg.ordermanagement.DTO.PurchaseOrderDTO;
import com.egg.ordermanagement.DTO.UserProfileDTO;
import com.egg.ordermanagement.converter.ProfileMapper;
import com.egg.ordermanagement.converter.ProfileMapperImpl;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.repositories.CustomerRepository;
import com.egg.ordermanagement.services.CustomerService;
import com.egg.ordermanagement.services.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;



@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProfileMapperImpl profileMapper;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("")
    public ResponseEntity<String> adminMenu(){

        return new ResponseEntity<>("menu de administrador", HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getCustomers(){

        //List<CustomerDTO> customerList = customerService.getAllCustomer();


        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.FOUND);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<InputStreamResource> getCustomerPic(@PathVariable Long id){

        UserProfileDTO userProfile = profileMapper.toDTO(customerRepository.findById(id).get());

        if (userProfile == null || userProfile.getImage() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        byte[] imageBytes = userProfile.getImage().getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        headers.set("Content-Disposition", "inline; filename=image.jpg");

        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageBytes.length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);

        /*byte[] imageBytes = userProfile.getImage().getContent();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        headers.set("Content-Disposition", "inline; filename=image.jpg");

        System.out.println(imageBytes);

        return new ResponseEntity<>(imageBytes,headers, HttpStatus.OK);

         */
    }




   /*@GetMapping("/customers/{id}")
    public ResponseEntity<UserProfileDTO> getCustomerData(@PathVariable Long id){

        UserProfileDTO userProfile = profileMapper.toDTO(customerRepository.findById(id).get());

        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    */



    @GetMapping("/orders")
    public ResponseEntity<List<PurchaseOrderDTO>> getPurchaseOrder(){

        List<PurchaseOrderDTO> orderList = purchaseOrderService.getAllPurchaseOrder();

        return new ResponseEntity<>(orderList,HttpStatus.FOUND);
    }
}
