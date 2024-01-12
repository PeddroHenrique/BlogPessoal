/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.blogpessoal.service;

import br.com.blogpessoal.dao.RegistrationDto;
import br.com.blogpessoal.model.Post;
import br.com.blogpessoal.model.Users;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface UsersService {

    Users save(RegistrationDto registrationDto);

    List<Users> findAll();

    Users findById(String id);

    void deleteById(String id);

    Users findByUsername(String username);

    Users addPostToUser(String userId, Post post);

    Post findPostByPostId(Users user, String postId);
    
    void deletePostById(String userId, String postId);
    
    List<Post> findPostsBySearch(String postName);

    List<Post> findAllPosts();
}
