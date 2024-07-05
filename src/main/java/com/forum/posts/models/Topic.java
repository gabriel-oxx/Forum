package com.forum.posts.models;

import com.forum.posts.models.dtos.RegisterTopicInput;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity(name = "Topic")
@Table(name = "topics")
@Getter
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Date creationDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	private String author;
	private String course;

	public Topic(RegisterTopicInput data, Date date) {
		this.title = data.title();
		this.description = data.description();
		this.status = data.status();
		this.author = data.author();
		this.course = data.course();
		this.creationDate = date;
	}


	public Topic() {

	}
}
