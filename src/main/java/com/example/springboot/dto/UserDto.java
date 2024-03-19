package com.example.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "User Name"
    )
    @NotEmpty(message = "User name should not be null or empty")
    private String name;

    @Schema(
            description = "User Username"
    )
    @NotEmpty(message = "User username should not be null or empty")
    private String username;

    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Schema(
            description = "User Address"
    )
    private Object address;

    @Schema(
            description = "User Phone"
    )
    private String phone;

    @Schema(
            description = "User Website"
    )
    private String website;

    @Schema(
            description = "User Company"
    )
    private Object company;
}
