/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.ordermanagement.services;

import com.egg.ordermanagement.DTO.PurchaseOrderDTO;
import com.egg.ordermanagement.converter.PurchaseOrderConverter;
import com.egg.ordermanagement.entities.PurchaseOrder;
import com.egg.ordermanagement.exceptions.MyException;
import com.egg.ordermanagement.repositories.PurchaseOrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SAMUEL
 */

@Service
public class PurchaseOrderService {
        
    
    @Autowired
    PurchaseOrderRepository PurchaseOrderRepo;
    
    @Autowired
    PurchaseOrderConverter POConverter;
    
    @Transactional
    public void createPurchaseOrder(PurchaseOrderDTO PurchaseOrderDTO) throws MyException{
        DTOvalidation(PurchaseOrderDTO);
        PurchaseOrder po = POConverter.toEntity(PurchaseOrderDTO);
        PurchaseOrderRepo.save(po);
    }
    
    @Transactional
    public void editPurchaseOrder(Long id, PurchaseOrderDTO PurchaseOrderDTO) throws MyException{
        DTOvalidation(PurchaseOrderDTO);
        PurchaseOrder p = IDvalidation(id);
        p = POConverter.toEntity(PurchaseOrderDTO);
        p.setId(id);
        PurchaseOrderRepo.save(p);
    }
    
    @Transactional
    public void deletePurchaseOrder(Long id) throws MyException{
        PurchaseOrder p = IDvalidation(id);
        PurchaseOrderRepo.delete(p);
    }
    
    public List<PurchaseOrderDTO> getAllPurchaseOrder(){
        List<PurchaseOrderDTO> purchaseOrdersDTO = new ArrayList();
        List<PurchaseOrder> purchaseOrders = PurchaseOrderRepo.findAll();
        
        purchaseOrders.forEach(po -> {
            try {
                purchaseOrdersDTO.add(POConverter.toDTO(po));
            } catch (MyException ex) {
                Logger.getLogger(PurchaseOrderService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        return purchaseOrdersDTO;
    }
    
    public PurchaseOrderDTO getPurchaseOrder(Long id) throws MyException{
        return POConverter.toDTO(IDvalidation(id));
    }
    
    private PurchaseOrder IDvalidation(Long id) throws MyException{
        Optional<PurchaseOrder> response = PurchaseOrderRepo.findById(id);
        if (response.isPresent()) {
            return response.get();
        } else{
            throw new MyException("El id ingresado no esta asociado a ningun PurchaseOrdero");
        }

    }
    
    private void DTOvalidation(PurchaseOrderDTO DTO) throws MyException{
        
        if(DTO.getDate()==null){
            throw new MyException("El nombre del PurchaseOrdero no puede ser vacio o estar nulo");
        }
        if(DTO.getAmount()==null){
            throw new MyException("El precio no puede ser vacio o estar nulo");
        }
        if(DTO.getCustomer()==null){
            throw new MyException("El precio no puede ser vacio o estar nulo");
        }        
    }

}
