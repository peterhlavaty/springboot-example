package com.example.springboot.service.external;

import com.example.springboot.dto.PostDto;
import com.example.springboot.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ExternalService {
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";


    public List<PostDto> getPosts() {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(Optional.ofNullable(
                restTemplate.getForEntity(POSTS_URL, PostDto[].class).getBody()).orElse(new PostDto[]{}));
    }

    public List<UserDto> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        return Arrays.asList(Optional.ofNullable(
                restTemplate.getForEntity(USERS_URL, UserDto[].class).getBody()).orElse(new UserDto[]{}));
    }
}
