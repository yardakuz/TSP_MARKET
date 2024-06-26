package com.example.tsp_market.services;


import com.example.tsp_market.models.Image;
import com.example.tsp_market.models.Order;
import com.example.tsp_market.models.Technique;
import com.example.tsp_market.models.User;
import com.example.tsp_market.repositories.OrderRepository;
import com.example.tsp_market.repositories.TechniqueRepository;
import com.example.tsp_market.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final TechniqueRepository techniqueRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private List<Technique> Cart = new ArrayList<>();

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
        else if (file2.getSize() != 0){
            image2 = toImageEntity(file2);
            image2.setPreviewImage(true);
            product.addImageToProduct(image2);
        }
        else if (file3.getSize() != 0){
            image3 = toImageEntity(file3);
            image3.setPreviewImage(true);
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
        if(!Cart.contains(getProductById(id))) Cart.add(getProductById(id));
        //иначе ++ к количеству.
    }

    public boolean isInCart(Long id){
        if (Cart.contains(getProductById(id))) return true;
        return false;
    }

    public List<Technique> getCart(){
        return Cart;
    }

    public List<Technique> delCart()
    {
        return Cart = new ArrayList<>();
    }

    public void deleteTechnique(Long id)
    {
        List<Long> orderIds = new ArrayList<>();
        List<Order> orders = orderRepository.findByTechniques(techniqueRepository.getById(id));
        if (orders == null)
        {
            techniqueRepository.deleteById(id);
            return;
        }
        for (Order order : orders) {
            orderIds.add(order.getId());
        }
        for (Long orderId : orderIds) {
            orderRepository.deleteById(orderId);
        }
        techniqueRepository.deleteById(id);
    }
}
