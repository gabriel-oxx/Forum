package com.forum.posts.models;

public enum Status {
	OPENED("Tópico aberto e aceita respostas"),
	CLOSED("Tópico fechado e não aceita mais respostas"),
	RESOLVED("O tópico foi resolvido e a questão foi resolvida");
	private final String description;

	Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
