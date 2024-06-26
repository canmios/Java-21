package com.devskiller.tasks.blog.model;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

	CommentDto toCommentDtoResponse(Comment comment);

}
