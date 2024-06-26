package com.devskiller.tasks.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String comment;
	private String author;

	private LocalDateTime creationDate;

}
