package com.example.baitap10.controller;

import com.example.baitap10.model.Category;
import com.example.baitap10.model.Product;
import com.example.baitap10.model.User;
import com.example.baitap10.repository.CategoryRepository;
import com.example.baitap10.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "admin/dashboard";
    }
    
    @GetMapping("/categories")
    public String categories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "admin/categories";
    }
    
    @PostMapping("/categories")
    public String addCategory(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("categories", categories);
            return "admin/categories";
        }
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/categories/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());
        return "admin/products";
    }
    
    @PostMapping("/products")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Product> products = productRepository.findAll();
            List<Category> categories = categoryRepository.findAll();
            model.addAttribute("products", products);
            model.addAttribute("categories", categories);
            return "admin/products";
        }
        productRepository.save(product);
        return "redirect:/admin/products";
    }
    
    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }
}
