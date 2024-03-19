package com.example.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.example.springboot.dto.PostDto;
import com.example.springboot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Post Resource",
        description = "CRUD REST APIs - Create Post, Update Post, Get Post, Get All Posts, Delete Post"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    private PostService postService;

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto post){
        PostDto savedPost = postService.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Post By ID REST API",
            description = "Get Post By ID REST API is used to get a single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Post By USER ID REST API",
            description = "Get Post By USER ID REST API is used to get posts from the database for given user id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable("userId") Long userId){
        List<PostDto> post = postService.getPostByUserId(userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to get a all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long postId,
                                              @RequestBody @Valid PostDto post){
        post.setId(postId);
        PostDto updatedPost = postService.updatePost(post);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("Post successfully deleted!", HttpStatus.OK);
    }
}
