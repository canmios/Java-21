package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.PostService;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;
	private final CommentService commentService;



	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)

	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}

	@RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Long> saveComments(@PathVariable("id") long userId,
												   @RequestBody NewCommentDto newCommentDto) {

		try {
			return new ResponseEntity<>(commentService.addComment(userId, newCommentDto),
				HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(userId, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}/comments")
	public List<CommentDto> getComments(@PathVariable("id") long id) {
		if (commentService.getCommentsForPost(id) == null)
			return List.of();
		else
			return commentService.getCommentsForPost(id);
	}


}
