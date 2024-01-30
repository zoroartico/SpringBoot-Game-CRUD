package com.example.springbootproject1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private int id;
    @NotBlank(message = "Title cannot be blank.")
    private String title;
    @Positive(message = "Rating must be positive.")
    @NotNull(message = "Rating cannot be null.")
    private double rating;
}
