package com.egg.ordermanagement.services;

import com.egg.ordermanagement.entities.Image;
import com.egg.ordermanagement.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(MultipartFile uploadedImg) {
        if(uploadedImg!=null) {
            try {
                Image image = new Image();

                image.setMime(uploadedImg.getContentType());

                image.setName(uploadedImg.getName());

                image.setContent(uploadedImg.getBytes());

                return imageRepository.save(image);
            }
            catch (Exception ex){
                //return null;
            }
        }
        return null;
    }


}
