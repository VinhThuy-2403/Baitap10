package com.example.baitap10.interceptor;

import com.example.baitap10.model.Role;
import com.example.baitap10.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        String requestURI = request.getRequestURI();
        
        if (user == null) {
            if (requestURI.startsWith("/admin") || requestURI.startsWith("/user")) {
                response.sendRedirect("/login");
                return false;
            }
            return true;
        }
        
        if (requestURI.startsWith("/admin")) {
            if (user.getRole() != Role.ADMIN) {
                response.sendRedirect("/login?error=access_denied");
                return false;
            }
        }
        
        if (requestURI.startsWith("/user")) {
            if (user.getRole() != Role.USER) {
                response.sendRedirect("/login?error=access_denied");
                return false;
            }
        }
        
        return true;
    }
}
