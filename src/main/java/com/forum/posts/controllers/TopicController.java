package com.forum.posts.controllers;

import com.forum.posts.models.Topic;
import com.forum.posts.models.dtos.RegisterTopicInput;
import com.forum.posts.models.dtos.RegisterTopicOutput;
import com.forum.posts.models.dtos.TopicDetails;
import com.forum.posts.models.dtos.UpdateTopic;
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

import java.time.ZoneId;
import java.time.ZonedDateTime;


@RestController
@RequestMapping("topicos")
public class TopicController {
	@Autowired
	TopicRepository topicRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<TopicDetails> addTopic(@Valid @RequestBody RegisterTopicInput data, UriComponentsBuilder uriBuilder) {
		ZonedDateTime date = ZonedDateTime.now(ZoneId.systemDefault());
		var topic = new Topic(data);
		topic.addDate(date);
		topicRepository.save(topic);
		var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicDetails(topic));
	}

	@GetMapping
	ResponseEntity<Page<RegisterTopicOutput>> getAllTopics(
			@PageableDefault(sort = {"creationDate"}, direction = Sort.Direction.DESC)
			Pageable pageable
	) {
		var page = topicRepository.findAll(pageable)
				           .map(RegisterTopicOutput::new);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity getTopic(@PathVariable(required = true) Long id) {

		var topic = topicRepository.getReferenceById(id);

		return ResponseEntity.ok(new TopicDetails(topic));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicDetails> updateTopic(@Valid @RequestBody UpdateTopic data) {
		ZonedDateTime date = ZonedDateTime.now(ZoneId.systemDefault());
		var topic = topicRepository.getReferenceById(data.id());

		if (data != null) {
			topic.addDate(date);
			topic.update(data);
			return ResponseEntity.ok(new TopicDetails(topic));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteTopic(@PathVariable Long id) {

		if (topicRepository.existsById(id)) {
			topicRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
}
