package com.auth.gestaoTarefas.api.security;


import com.auth.gestaoTarefas.api.models.UserModel;
import com.auth.gestaoTarefas.api.repository.UserRepository;
import com.auth.gestaoTarefas.api.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * Esta classe é um filtro personalizado utilizado em um aplicativo Spring para implementar a
 * autenticação com base em token (token-based authentication) ou autenticação stateless.
 */

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authenticationl = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationl != null) {
            UserDetails userDetails = (UserDetails) authenticationl.getPrincipal();
            var token = this.recoverToken(request);
            if (token != null) {
                var nome = tokenService.validateToken(token);
                UserDetails user = userRepository.findByname(nome);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}