package com.example.baitap10.config;

import com.example.baitap10.model.Category;
import com.example.baitap10.model.Product;
import com.example.baitap10.model.Role;
import com.example.baitap10.model.User;
import com.example.baitap10.repository.CategoryRepository;
import com.example.baitap10.repository.ProductRepository;
import com.example.baitap10.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User("admin", "admin123", "admin@example.com", Role.ADMIN);
            User user = new User("user", "user123", "user@example.com", Role.USER);
            userRepository.save(admin);
            userRepository.save(user);
        }
        
        if (categoryRepository.count() == 0) {
            Category electronics = new Category("Điện tử", "Sản phẩm điện tử");
            Category clothing = new Category("Thời trang", "Quần áo thời trang");
            categoryRepository.save(electronics);
            categoryRepository.save(clothing);
        }
        
        if (productRepository.count() == 0) {
            Category electronics = categoryRepository.findAll().get(0);
            Category clothing = categoryRepository.findAll().get(1);
            
            Product laptop = new Product("Laptop Dell", "Laptop Dell Inspiron 15", new BigDecimal("15000000"), 10, electronics);
            Product phone = new Product("iPhone 15", "iPhone 15 Pro Max", new BigDecimal("25000000"), 5, electronics);
            Product shirt = new Product("Áo sơ mi", "Áo sơ mi nam cao cấp", new BigDecimal("500000"), 20, clothing);
            
            productRepository.save(laptop);
            productRepository.save(phone);
            productRepository.save(shirt);
        }
    }
}
