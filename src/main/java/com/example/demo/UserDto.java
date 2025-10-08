package com.example.demo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

//@Data
@Schema(description = "DTO пользователя")
public class UserDto {
    @Schema(description = "Уникальный идентификатор пользователя", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Имя пользователя", example = "Иван Иванов", required = true)
    private String username;

    @Email(message = "Email should be valid")
    @Schema(description = "Email пользователя", example = "ivan.ivanov@example.com", required = true)
    private String email;


    // геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

