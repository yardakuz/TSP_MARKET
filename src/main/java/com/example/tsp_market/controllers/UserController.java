package com.example.tsp_market.controllers;

import com.example.tsp_market.models.User;
import com.example.tsp_market.repositories.OrderRepository;
import com.example.tsp_market.services.OrderService;
import com.example.tsp_market.services.ProductService;
import com.example.tsp_market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class  UserController {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if(!userService.createUser(user)){
            model.addAttribute("error", "Username or password is incorrect");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal){
        if (!productService.getUserByPrincipal(principal).isAdmin()) {return "error";}
        model.addAttribute("user", user);
        model.addAttribute("principal", userService.getUserByPrincipal(principal));
        model.addAttribute("orders", orderRepository.findByUserId(user.getId()));
        return "user-info";
    }

}
