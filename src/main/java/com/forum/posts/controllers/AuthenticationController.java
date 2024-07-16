package com.forum.posts.controllers;

import com.forum.posts.models.User;
import com.forum.posts.models.dtos.UserData;
import com.forum.posts.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	private final UserRepository userRepository;

	public AuthenticationController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	public ResponseEntity hello() {
		return ResponseEntity.ok("Hello World");
	}

	@PostMapping
	@Transactional
	public ResponseEntity signIn(@Valid @RequestBody UserData data) {
		var user = new User(data);
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}
}
