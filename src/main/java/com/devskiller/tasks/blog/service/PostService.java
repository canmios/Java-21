package com.devskiller.tasks.blog.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.repository.PostRepository;

@Service
@AllArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public PostDto getPost(Long id) {
		return postRepository.findById(id)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElse(null);
	}


}
