package com.forum.posts.models.dtos;

import com.forum.posts.models.Status;
import com.forum.posts.models.Topic;
import jakarta.validation.constraints.NotBlank;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public record TopicDetails(
		Long id,
		String title,
		String description,
		Status status,
		ZonedDateTime creationDate,
		String author,
		String course
) {
	public TopicDetails(Topic topic){
this(
		topic.getId(),
		topic.getTitle(),
		topic.getDescription(),
		topic.getStatus(),
		topic.getCreationDate().toInstant().atZone(ZoneId.systemDefault()),
		topic.getAuthor(),
		topic.getCourse()
);
	}

}