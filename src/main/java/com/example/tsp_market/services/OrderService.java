package com.example.tsp_market.services;

import com.example.tsp_market.models.User;
import com.example.tsp_market.models.Order;
import com.example.tsp_market.models.enums.Role;
import com.example.tsp_market.repositories.OrderRepository;
import com.example.tsp_market.repositories.UserRepository;
import com.example.tsp_market.models.Technique;
import com.example.tsp_market.repositories.TechniqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TechniqueRepository techniqueRepository;
    private final ProductService productService;



    public boolean createOrder(Order order, List<Technique> techniques, Principal principal) {
        int price = 0;
        for (Technique technique : techniques) {
            price += technique.getPrice();
        }
        order.setPrice(price);
        order.setActive(true);
        order.setUser(productService.getUserByPrincipal(principal));
        order.setTechniques(techniques);
        log.info("Saving new order");
        orderRepository.save(order);
        return true;
    }

    public List<Order> list(){
        return orderRepository.findAll();
    }

    public List<Order> userlist(Principal principal){
        return orderRepository.findByUserId(productService.getUserByPrincipal(principal).getId());
    }

    public void changeOrderActive(Order order) {
       order.setActive(!order.isActive());
        orderRepository.save(order);
    }

    public void deleteOrder(Order order) {

    }
}
