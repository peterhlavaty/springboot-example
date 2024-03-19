package com.example.springboot.service;

import com.example.springboot.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto post);

    PostDto getPostById(Long postId);

    List<PostDto> getPostByUserId(Long userId);

    List<PostDto> getAllPosts();

    PostDto updatePost(PostDto post);

    void deletePost(Long postId);
}
