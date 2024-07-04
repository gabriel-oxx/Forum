package com.forum.posts.controllers;

import com.forum.posts.models.Topic;
import com.forum.posts.models.dtos.RegisterTopic;
import com.forum.posts.models.dtos.TopicDetails;
import com.forum.posts.repositories.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;


@RestController
@RequestMapping("topicos")
public class TopicController {
	@Autowired
	TopicRepository topicRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<TopicDetails> addTopic(@Valid @RequestBody RegisterTopic data, UriComponentsBuilder uriBuilder) {
		Date date = new Date();
		var topic = new Topic(data, date);
		topicRepository.save(topic);
		var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDetails(topic));
	}
}
