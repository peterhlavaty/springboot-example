package com.example.springboot.mapper;

import com.example.springboot.dto.PostDto;
import com.example.springboot.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);

    PostDto mapToPostDto(Post post);

    Post mapToPost(PostDto postDto);

    void updatePostFromDto(PostDto postDto, @MappingTarget Post post);
}
