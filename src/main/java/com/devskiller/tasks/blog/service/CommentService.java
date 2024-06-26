package com.devskiller.tasks.blog.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.CommentMapper;
import com.devskiller.tasks.blog.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
@AllArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostService postService;

	private final CommentMapper commentMapper = CommentMapper.INSTANCE;
	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		List<Comment> comments = commentRepository.findAll().stream()
			.filter(c -> c.getId() == postId)
			.sorted(Comparator.comparing(Comment::getCreationDate).reversed())
			.collect(Collectors.toList());
		//return comments.stream().map()
		return comments.stream().map(commentMapper::toCommentDtoResponse).collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param postId id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {
		if(postService.getPost(postId) ==null){
			throw new IllegalArgumentException();
		}else{
			 Comment s = commentRepository.save(Comment.builder()
					 .author(newCommentDto.author())
					 .comment(newCommentDto.content())
				     .creationDate(LocalDateTime.now())
				 .build());
			 return s.getId();
		}
	}
}
