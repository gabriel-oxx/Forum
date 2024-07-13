package com.forum.posts.models.dtos;

import com.forum.posts.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopic(
		Long id,
		@NotBlank(message = "Título é obrigatório")
		String title,
		@NotBlank(message = "Descrição do problema é obrigatória")
		String description,
		@NotNull(message = "Status do tópico é obrigatório")
		Status status
) {
}
