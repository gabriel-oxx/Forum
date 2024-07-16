package com.forum.posts.models;

import com.forum.posts.models.dtos.UserData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "users")
@Entity(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	private String userName;
	private String password;

	public User(UserData data) {
this.id = data.id();
this.userName = data.userName();
this.password = data.password();
	}
}
