package com.auth.gestaoTarefas.api.dto;

import com.auth.gestaoTarefas.api.models.enums.UserRole;

public record RegisterUserDTO(String name, String Password, UserRole role){
}
