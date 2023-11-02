package com.auth.gestaoTarefas.api.controllers;

import com.auth.gestaoTarefas.api.dto.TaskDTO;
import com.auth.gestaoTarefas.api.models.TaskModel;
import com.auth.gestaoTarefas.api.models.UserModel;
import com.auth.gestaoTarefas.api.repository.UserRepository;
import com.auth.gestaoTarefas.api.services.TaskServices;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins= "*", maxAge = 3600)
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskServices taskServices;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody @Valid TaskDTO taskDTO){
        Optional<UserModel> userM = userRepository.findById(taskDTO.userid());
        var taskModel = new TaskModel();
        if (userM.isPresent()){
            BeanUtils.copyProperties(taskDTO,taskModel);
            taskServices.save(taskModel);
            return ResponseEntity.ok(taskModel);
        }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id do user nao existe!");
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<List<TaskModel>> listTask(@PathVariable(value = "UserId") UUID userId) {
        List<TaskModel> tasks = taskServices.findAllByUserId(userId);
        return ResponseEntity.ok(tasks);
    }
}
