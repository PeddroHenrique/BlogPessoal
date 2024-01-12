/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Pedro
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @NotBlank
    @Size(min = 4, max = 20)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private LocalDate birthDate;
    @Min(value = 0)
    private int age;
    @NotBlank
    private String location;
    @NotBlank
    @Size(min = 0, max = 250)
    private String biography;
    private byte[] avatar;
    
}