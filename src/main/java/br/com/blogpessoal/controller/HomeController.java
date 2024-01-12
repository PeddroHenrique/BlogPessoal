/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.controller;

import br.com.blogpessoal.model.Post;
import br.com.blogpessoal.security.SecurityUtil;
import br.com.blogpessoal.service.UsersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Pedro
 */
@Controller
@RequestMapping("/homepage")
@RequiredArgsConstructor
public class HomeController {

    private final UsersService usersService;

    @GetMapping()
    public String homePage(Model model) {
        String username = SecurityUtil.getSession();
        model.addAttribute("user", usersService.findByUsername(username));
        model.addAttribute("search", false);
        return "homepage/homepage";
    }

    @PostMapping()
    public String searchAction(@RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "searchAll", required = false) String searchAll, Model model) {
        String username = SecurityUtil.getSession();

        if (searchAll != null) {
            List<Post> allPosts = usersService.findAllPosts();
            model.addAttribute("posts", allPosts);
        }
        
        if (search != null) {
            search.trim();
            search.replace("\\s+", " ");
            
            List<Post> posts = usersService.findPostsBySearch(search);
            model.addAttribute("posts", posts);
            model.addAttribute("searchName", search);
        }
        
        model.addAttribute("user", usersService.findByUsername(username));
        model.addAttribute("search", true);
        return "homepage/homepage";
    }
}
