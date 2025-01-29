package com.example.demo.controllers;


import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BuyNowController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long productID, RedirectAttributes redirectAttributes) {
        Optional<Product> optionalProduct = productRepository.findById(productID);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Check if inventory is greater than 0
            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1); // Decrement inventory
                productRepository.save(product);    // Save updated product
                redirectAttributes.addFlashAttribute("message", "Purchase successful!");
                return "redirect:/purchaseSuccess";
            } else {
                redirectAttributes.addFlashAttribute("error", "Purchase failed: Out of stock.");
                return "redirect:/purchaseError";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Purchase failed: Product not found.");
            return "redirect:/purchaseError";
        }
    }

    // Mapping for the purchase success page
    @GetMapping("/purchaseSuccess")
    public String displayPurchaseSuccess() {
        return "purchaseSuccess"; // Refers to purchaseSuccess.html
    }

    // Mapping for the purchase error page
    @GetMapping("/purchaseError")
    public String displayPurchaseError() {
        return "purchaseError"; // Refers to purchaseError.html
    }





}
