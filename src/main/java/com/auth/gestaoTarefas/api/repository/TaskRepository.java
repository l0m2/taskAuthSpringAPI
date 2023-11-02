package com.auth.gestaoTarefas.api.repository;

import com.auth.gestaoTarefas.api.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findAllByuserid(UUID UserId);
}
