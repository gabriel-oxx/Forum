package com.forum.posts.controllers;

import com.forum.posts.models.Topic;
import com.forum.posts.models.dtos.RegisterTopicInput;
import com.forum.posts.models.dtos.RegisterTopicOutput;
import com.forum.posts.models.dtos.TopicDetails;
import com.forum.posts.repositories.TopicRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	public ResponseEntity<TopicDetails> addTopic(@Valid @RequestBody RegisterTopicInput data, UriComponentsBuilder uriBuilder) {
		Date date = new Date();
		var topic = new Topic(data, date);
		topicRepository.save(topic);
		var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDetails(topic));
	}

	@GetMapping
	ResponseEntity<Page<RegisterTopicOutput>> getAllTopics(
			@PageableDefault(size = 10, sort = {"creationDate"}, direction = Sort.Direction.DESC)
			Pageable pageable
	) {
		var page = topicRepository.findAll(pageable)
				           .map(RegisterTopicOutput::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
public ResponseEntity getTopic(@PathVariable(required = true) Long id){

		var topic = topicRepository.getReferenceById(id);

		return ResponseEntity.ok(new TopicDetails(topic));
	}

}
