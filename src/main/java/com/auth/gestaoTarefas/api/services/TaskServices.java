package com.auth.gestaoTarefas.api.services;

import com.auth.gestaoTarefas.api.models.TaskModel;
import com.auth.gestaoTarefas.api.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServices {

    @Autowired
    TaskRepository taskRepository;

    public TaskServices(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> findAllByUserId(UUID userId) {
        return taskRepository.findAllByuserid(userId);
    }

    @Transactional
    public TaskModel save(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }

    public Optional<TaskModel> findById(UUID Id){
        return taskRepository.findById(Id);
    }

    @Transactional
    public void delete(TaskModel task){
        taskRepository.delete(task);
    }
}
