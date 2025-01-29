package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the About page.
 */
@Controller
public class AboutController {

    /**
     * Maps the About page template to the URL "/about".
     * When accessed, this method renders the about.html template.
     */
    @GetMapping("/about")
    public String showAboutPage() {
        // Return the name of the Thymeleaf template (about.html)
        return "about";
    }
}