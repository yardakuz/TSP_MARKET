package com.example.tsp_market.controllers;

import com.example.tsp_market.models.*;
import com.example.tsp_market.models.enums.Role;
import com.example.tsp_market.repositories.ImageRepository;
import com.example.tsp_market.repositories.TechniqueRepository;
import com.example.tsp_market.repositories.UserRepository;
import com.example.tsp_market.services.OrderService;
import com.example.tsp_market.services.ProductService;
import com.example.tsp_market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

//@RestController
@RequiredArgsConstructor
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final TechniqueRepository techniqueRepository;
    private OrderTechnique orderTechnique;
    private final UserRepository userRepository;


    @GetMapping("/admin/order")
    public String orderOpen(Model model){
        model.addAttribute("orders", orderService.list());
        return "order_panel";
    }

    @GetMapping("/order/history")
    public String orderHistory(Model model, Principal principal){
        model.addAttribute("orders", orderService.listByUser(principal));
        return "order-history";
    }

    //создание заказа
    @PostMapping("/order/create")
    public String createOrder(Order order,Technique technique, Principal principal, @RequestParam("techniques[]") Long[] techniqueIds, Model model){
        List<Technique> techniques = techniqueRepository.findAllById(List.of(techniqueIds));
        //techniques.add(technique);
        orderService.createOrder(order, techniques, principal);
        productService.delCart();
        return "redirect:/";
    }

    //Обновление состояния заказа

    @GetMapping("/admin/order/edit/{order}")
    public String orderEdit(@PathVariable("order") List<Technique> order, Model model){
        model.addAttribute("order", order);
        //model.addAttribute("roles", Role.values());
        return "order-edit";
    }


    @PostMapping("/order/active/{order}")
    public String orderEdit(@PathVariable("order") Order order){
        orderService.changeOrderActive(order);
        return "redirect:/admin/order";
    }


}
