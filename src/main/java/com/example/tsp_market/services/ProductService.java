package com.example.tsp_market.services;


import com.example.tsp_market.models.Image;
import com.example.tsp_market.models.Technique;
import com.example.tsp_market.models.User;
import com.example.tsp_market.repositories.TechniqueRepository;
import com.example.tsp_market.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final TechniqueRepository techniqueRepository;
    private final UserRepository userRepository;
    private List<Long> Cart;

    public List<Technique> listProducts(String title) {
        if(title != null) return techniqueRepository.findByTitle(title);
        return techniqueRepository.findAll();
    }

    public void saveProduct(Principal principal, Technique product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0){
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0){
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }

        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getTitle());
        Technique productFromDb = techniqueRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        techniqueRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void removeProduct(Long id) {
        techniqueRepository.deleteById(id);
    }

    public Technique getProductById(Long id) {
       return techniqueRepository.findById(id).orElse(null);
    }

    public void AddToCart(Long id){
        Cart.add(id);
    }

    public Technique getCart(List<Long> Cart){
        for (int i = 0; i < Cart.size(); i++){

        }
    }
}
