package com.auth.gestaoTarefas.api.dto;

import java.util.UUID;

public record TaskDTO(String Title, String Description, UUID userid) {
}
