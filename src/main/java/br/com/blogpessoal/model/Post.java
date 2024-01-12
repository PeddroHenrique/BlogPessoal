/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Pedro
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private String postId;
    @NotBlank
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[\\p{L} \\p{M} \\d]+$")
    private String title;
    @NotBlank
    @Size(min = 0, max = 2000)
    private String content;
    private LocalDateTime publicationTime;
}
