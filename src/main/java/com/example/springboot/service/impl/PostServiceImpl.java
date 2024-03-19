package com.example.springboot.service.impl;

import com.example.springboot.dto.PostDto;
import com.example.springboot.entity.Post;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.mapper.PostMapper;
import com.example.springboot.repository.PostRepository;
import com.example.springboot.service.PostService;
import com.example.springboot.service.external.ExternalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ExternalService externalService;


    @Override
    public PostDto createPost(PostDto postDto) {
        if (externalService.getUsers().stream().noneMatch(e -> e.getId().equals(postDto.getUserId()))) {
            throw new ResourceNotFoundException("User", "id", postDto.getUserId());
        }

        Post post = PostMapper.MAPPER.mapToPost(postDto);
        Post savedPost = postRepository.save(post);
        return PostMapper.MAPPER.mapToPostDto(savedPost);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Optional<Post> postOp = postRepository.findById(postId);
        if (postOp.isPresent()) {
            return PostMapper.MAPPER.mapToPostDto(postOp.get());
        }
        Post post = externalService.getPosts().stream()
                .filter(e -> e.getId().equals(postId))
                .findAny()
                .map(PostMapper.MAPPER::mapToPost).orElseThrow(
                        () -> new ResourceNotFoundException("Post", "id", postId)
                );
        return PostMapper.MAPPER.mapToPostDto(postRepository.save(post));
    }

    @Override
    public List<PostDto> getPostByUserId(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return posts.stream().map(PostMapper.MAPPER::mapToPostDto).toList();
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper.MAPPER::mapToPostDto).toList();
    }

    @Override
    public PostDto updatePost(PostDto post) {
        Post existingPost = postRepository.findById(post.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", post.getId())
        );
        PostMapper.MAPPER.updatePostFromDto(post, existingPost);
        Post updatedPost = postRepository.save(existingPost);
        return PostMapper.MAPPER.mapToPostDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {

        postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId)
        );

        postRepository.deleteById(postId);
    }
}
