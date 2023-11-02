package com.auth.gestaoTarefas.api.repository;

import com.auth.gestaoTarefas.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByname(String Name);
}
