package com.egg.ordermanagement;

import com.egg.ordermanagement.DTO.ProductDTO;
import com.egg.ordermanagement.controllers.ProductController;
import com.egg.ordermanagement.converter.ProductMapper;
import com.egg.ordermanagement.converter.ProductMapperImpl;
import com.egg.ordermanagement.entities.Product;
import com.egg.ordermanagement.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    private ProductMapper productMapper = new ProductMapperImpl();;

/*    @Test
    public void shouldCreateProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO("producto prueba", 500);

        when(productService.createProduct(any(ProductDTO.class))).thenAnswer(invocation -> {
            Product product = productMapper.toEntity(invocation.getArgument(0));
            product.setId(1L);
            return product;
        });

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"producto prueba\",\"price\":500}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id': 1,'name':'producto prueba','price':500}"));
    }

    @Test
    public void shouldReturnProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO("producto prueba", 500);
        Long id = 1L;
        when(productService.getProduct((any(Long.class)))).thenReturn(productDTO);

        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json("{'name':'producto prueba','price':500}"));
    }

    @Test
    public void shouldNotFound() throws Exception {

        Long id = 1L;
        when(productService.getProduct((any(Long.class)))).thenReturn(null);

        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().isNotFound());
    }*/

}
