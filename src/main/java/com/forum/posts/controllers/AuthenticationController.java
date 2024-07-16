package com.forum.posts.controllers;

import com.forum.posts.infra.security.TokenData;
import com.forum.posts.infra.security.TokenService;
import com.forum.posts.models.User;
import com.forum.posts.models.dtos.UserData;
import com.forum.posts.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;

	public AuthenticationController(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@GetMapping
	public ResponseEntity hello() {
		return ResponseEntity.ok("Hello World");
	}

	@PostMapping
	@Transactional
	public ResponseEntity signIn(@Valid @RequestBody UserData data) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var authentication = authenticationManager.authenticate(authenticationToken);
		var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(new TokenData(tokenJWT));
	}
}
