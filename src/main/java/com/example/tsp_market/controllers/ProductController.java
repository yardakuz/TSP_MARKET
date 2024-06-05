package com.example.tsp_market.controllers;

import com.example.tsp_market.models.Technique;
import com.example.tsp_market.services.ProductService;
import com.example.tsp_market.services.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class   ProductController {
    private final ProductService productService;


    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Principal principal, Model model){
        Technique product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, Technique product, Principal principal) throws IOException {
        if(file1.getSize() == 0) {return "redirect:/error";}
        else {
            productService.saveProduct(principal, product, file1, file2, file3);
            return "redirect:/";
        }
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteTechnique(id);
        return "redirect:/";
    }

    @PostMapping("/product/cart/{id}")
    public String addToCart(@PathVariable Long id) {
        productService.AddToCart(id);
        return "redirect:/";
    }

    @GetMapping("/user-cart")
    public String cart(Principal principal, Model model) {
        if (productService.getUserByPrincipal(principal).isAdmin()) {return "error";}
        model.addAttribute("cart", productService.getCart());
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "cart";
    }

}
