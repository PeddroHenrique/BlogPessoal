/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.controller;

import br.com.blogpessoal.model.Post;
import br.com.blogpessoal.model.Users;
import br.com.blogpessoal.security.SecurityUtil;
import br.com.blogpessoal.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Pedro
 */
@Controller
@RequestMapping("/homepage")
@RequiredArgsConstructor
public class PostController {
    private final UsersService usersService;
    
    @GetMapping("/new-post")
    public String newPost(Post post, Model model) {
        String username = SecurityUtil.getSession();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);
        return "post/new-post";
    }
    
    @PostMapping("/new-post")
    public String savePost(@Valid @ModelAttribute("post")Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "post/new-post";
        }
        
        post.setTitle(post.getTitle().trim());
        post.setTitle(post.getTitle().replace("\\s+", " "));
        
        String currentUsername = SecurityUtil.getSession();
        if (currentUsername != null) {
            Users user = usersService.findByUsername(currentUsername);
            usersService.addPostToUser(user.getId(), post);
        }
        return "redirect:/homepage";
    }
    
    @GetMapping("/edit-post/{id}")
    public String editPost(@PathVariable("id")String id, Model model) {
        String currentUsername = SecurityUtil.getSession();
        Users user = usersService.findByUsername(currentUsername);
        model.addAttribute("user", user);
        model.addAttribute("post", usersService.findPostByPostId(user, id));
        return "post/edit-post";
    }
    
    @PostMapping("/delete-post/{id}")
    public String deletePost(@PathVariable("id")String id) {
        String currentUsername = SecurityUtil.getSession();
        Users user = usersService.findByUsername(currentUsername);
        usersService.deletePostById(user.getId(), id);
        return "redirect:/homepage";
    }
}
