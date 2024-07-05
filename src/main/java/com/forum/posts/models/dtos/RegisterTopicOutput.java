package com.forum.posts.models.dtos;

import com.forum.posts.models.Status;
import com.forum.posts.models.Topic;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public record RegisterTopicOutput(
		String title,
		String description,
		Status status,
		ZonedDateTime creationDate,
		String author,
		String course
) {
	public RegisterTopicOutput(Topic data){
		this(
				data.getTitle(),
				data.getDescription(),
				data.getStatus(),
				data.getCreationDate().toInstant().atZone(ZoneId.systemDefault()),
				data.getAuthor(),
				data.getCourse()
		);
	}
}
