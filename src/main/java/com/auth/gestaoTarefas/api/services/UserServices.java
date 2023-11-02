package com.auth.gestaoTarefas.api.services;

import com.auth.gestaoTarefas.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

}
