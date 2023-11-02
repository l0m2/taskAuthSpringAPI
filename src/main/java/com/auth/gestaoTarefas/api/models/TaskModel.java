package com.auth.gestaoTarefas.api.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "Tasks")
public class TaskModel implements Serializable {
   private static final long Serial = 1L;

   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
   @Column(nullable = false)
    private String Title;
   @Column(nullable = true)
    private String Description;
   @Column(name = "userid")
    private UUID userid;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userModel) {
        this.userid = userModel;
    }
}
