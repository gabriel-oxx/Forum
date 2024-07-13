package com.forum.posts.models;

import com.forum.posts.models.dtos.RegisterTopicInput;
import com.forum.posts.models.dtos.UpdateTopic;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.ZonedDateTime;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private ZonedDateTime creationDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String author;
	private String course;

	public Topic(RegisterTopicInput data) {
		this.title = data.title();
		this.description = data.description();
		this.status = data.status();
		this.author = data.author();
		this.course = data.course();
	}
	public Topic() {

	}

	public void update(UpdateTopic data) {

		if (data.title() != null && !this.title.equals(data.title())) {
			this.title = data.title();
		}

		if (data.description() != null && !this.description.equals(data.description())) {
			this.description = data.description();
		}

		if (data.status() != null && !this.status.equals(data.status())) {
			this.status = data.status();
		}

	}

	public void addDate (ZonedDateTime date) {
		this.creationDate = date;
	}

}
