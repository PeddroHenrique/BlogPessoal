/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.dao;

import br.com.blogpessoal.model.Profile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class RegistrationDto {
    @NotBlank()
    @Size(min = 4, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String username;
    @NotBlank
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;
    @Valid
    private Profile profile;
}
