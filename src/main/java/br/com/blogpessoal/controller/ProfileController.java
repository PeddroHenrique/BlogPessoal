/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.controller;

import br.com.blogpessoal.model.Users;
import br.com.blogpessoal.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pedro
 */
@Controller
@RequestMapping("homepage")
@RequiredArgsConstructor
public class ProfileController {
    private final UsersService usersService;
    
    @GetMapping("/user-profile/{id}")
    public String userProfile(@PathVariable("id")String id, Model model) {
        Users user = usersService.findById(id);
        model.addAttribute("user", user);
        return "profile/user-profile";
    }
}
