package com.example.baitap10.controller;

import com.example.baitap10.dto.LoginRequest;
import com.example.baitap10.dto.RegisterRequest;
import com.example.baitap10.model.Role;
import com.example.baitap10.model.User;
import com.example.baitap10.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@Valid LoginRequest loginRequest, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        
        User user = userService.login(loginRequest);
        if (user == null) {
            model.addAttribute("error", "Username hoặc password không đúng");
            model.addAttribute("loginRequest", new LoginRequest());
            return "login";
        }
        
        session.setAttribute("user", user);
        
        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@Valid RegisterRequest registerRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
        if (userService.existsByUsername(registerRequest.getUsername())) {
            result.rejectValue("username", "error.username", "Username đã tồn tại");
            return "register";
        }
        
        if (userService.existsByEmail(registerRequest.getEmail())) {
            result.rejectValue("email", "error.email", "Email đã tồn tại");
            return "register";
        }
        
        userService.register(registerRequest);
        model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
