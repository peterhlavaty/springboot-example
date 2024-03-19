package com.example.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "PostDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @Schema(
            description = "User id"
    )
    @NotNull(message = "User id should not be null")
    private Long userId;

    @Schema(
            description = "Post title"
    )
    @NotEmpty(message = "Post title should not be null or empty")
    private String title;

    @Schema(
            description = "Post body"
    )
    @NotEmpty(message = "Post body should not be null or empty")
    private String body;
}
