package com.devskiller.tasks.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Post {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	@Column(length = 4096)
	private String content;

	private LocalDateTime creationDate;

}
