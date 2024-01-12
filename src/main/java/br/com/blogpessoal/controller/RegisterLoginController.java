/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.controller;

import br.com.blogpessoal.dao.RegistrationDto;
import br.com.blogpessoal.model.Users;
import br.com.blogpessoal.service.UsersService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Pedro
 */
@Controller
@RequiredArgsConstructor
public class RegisterLoginController {

    private final UsersService usersService;

    @GetMapping("/login")
    public String login() {
        return "registerlogin/login";
    }

    @GetMapping("/register")
    public String register(RegistrationDto registrationDto) {
        return "registerlogin/register";
    }

    @PostMapping("/register")
    public String registerSave(@Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto,
            BindingResult result, @RequestParam("avatarFile") MultipartFile avatarFile, Model model) {
        
        long maxFileSize = 1 * 1024 * 1024;
        
        if (avatarFile != null && !avatarFile.isEmpty()) {
            if (avatarFile.getSize() > maxFileSize) {
                result.rejectValue("profile.avatar", "avatar.size");
            } else {
                try {
                    registrationDto.getProfile().setAvatar(avatarFile.getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    result.rejectValue("profile.avatar", "avatar.empty");
                }
            }
        } else {
            result.rejectValue("profile.avatar", "avatar.empty");
        }

        if (result.hasErrors()) {
            return "registerlogin/register";
        }

        Users existingUsername = usersService.findByUsername(registrationDto.getUsername());
        if ((existingUsername != null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty())) {
            return "redirect:/register?fail";
        }

        usersService.save(registrationDto);
        return "redirect:/login";
    }

}
