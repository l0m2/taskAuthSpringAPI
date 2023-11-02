package com.auth.gestaoTarefas.api.controllers;


import com.auth.gestaoTarefas.api.dto.LoginUserDTO;
import com.auth.gestaoTarefas.api.dto.RegisterUserDTO;
import com.auth.gestaoTarefas.api.dto.TokenDTO;
import com.auth.gestaoTarefas.api.models.UserModel;
import com.auth.gestaoTarefas.api.repository.UserRepository;
import com.auth.gestaoTarefas.api.services.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterUserDTO registerUserDTO){
       String cryptPass = new BCryptPasswordEncoder().encode(registerUserDTO.Password());
        UserModel usuario = new UserModel(registerUserDTO.name(), cryptPass, registerUserDTO.role());
        UserModel salvo = userRepository.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUserDTO login){
        var UsuarioNP = new UsernamePasswordAuthenticationToken(login.Name(), login.Password());
        var auth = this.authenticationManager.authenticate(UsuarioNP);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return  ResponseEntity.ok(new TokenDTO(token));
    }
}
