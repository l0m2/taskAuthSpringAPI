package com.auth.gestaoTarefas.api.services;

import com.auth.gestaoTarefas.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Essa class eh responsavel por atraves do Username ela fornecer os detalhes do usuario
    @Service
    public class AutorizationService implements UserDetailsService {

        @Autowired
        UserRepository repository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return repository.findByname(username);
        }
    }

