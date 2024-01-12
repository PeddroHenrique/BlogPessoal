/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.service.impl;

import br.com.blogpessoal.dao.RegistrationDto;
import br.com.blogpessoal.model.Post;
import br.com.blogpessoal.model.Profile;
import br.com.blogpessoal.model.Role;
import br.com.blogpessoal.model.Users;
import br.com.blogpessoal.repository.RoleRepository;
import br.com.blogpessoal.repository.UsersRepository;
import br.com.blogpessoal.service.UsersService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pedro
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MongoTemplate mongoTemplate;

    @Override
    public Users save(RegistrationDto registrationDto) {
        Users user = new Users();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));

        if (user.getProfile() == null) {
            user.setProfile(new Profile());
        }
        user.getProfile().setName(registrationDto.getProfile().getName());
        user.getProfile().setBirthDate(registrationDto.getProfile().getBirthDate());
        user.getProfile().setAge(registrationDto.getProfile().getAge());
        user.getProfile().setLocation(registrationDto.getProfile().getLocation());
        user.getProfile().setBiography(registrationDto.getProfile().getBiography());
        user.getProfile().setAvatar(registrationDto.getProfile().getAvatar());
        user.setRegistrationDate(LocalDateTime.now());
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(String id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Users addPostToUser(String userId, Post post) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getPosts() == null) {
            user.setPosts(new ArrayList<>());
        }
        if (post.getPostId() != null) {
            Optional<Post> existingPost = user.getPosts().stream()
                    .filter(p -> p.getPostId().equals(post.getPostId()))
                    .findFirst();
            if (existingPost.isPresent()) {
                Post updatedPost = existingPost.get();
                if (updatedPost.getTitle().equals(post.getTitle()) && updatedPost.getContent().equals(post.getContent())) {
                    return null;
                }
                updatedPost.setTitle(post.getTitle());
                updatedPost.setContent(post.getContent());
            }
        } else {
            post.setPostId(new ObjectId().toString());
            post.setPublicationTime(LocalDateTime.now());
            user.getPosts().add(post);
        }

        return usersRepository.save(user);
    }

    @Override
    public Post findPostByPostId(Users user, String postId) {
        return user.getPosts().stream()
                .filter(post -> post.getPostId().equals(postId))
                .findFirst().orElse(null);
    }

    @Override
    public void deletePostById(String userId, String postId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getPosts() != null) {
            user.getPosts().removeIf(post -> post.getPostId().equals(postId));
            usersRepository.save(user);
        } 
    }

    @Override
    public List<Post> findPostsBySearch(String title) {
        List<Users> users = usersRepository.findAll();
        List<Post> postsByTitle = new ArrayList();
        
        for (Users user : users) {
            if (user.getPosts() != null) {
                List<Post> userPosts = user.getPosts();
                for (Post post : userPosts) {
                    if (post.getTitle().equalsIgnoreCase(title)) {
                        postsByTitle.add(post);
                    }
                }
            }
        }
        
        postsByTitle.sort(Comparator.comparing(Post::getPublicationTime)) /*Primeira maneira de implementar um Comparator*/;
        return postsByTitle;
    }

    @Override
    public List<Post> findAllPosts() {
        List<Users> users = usersRepository.findAll();
        List<Post> allPosts = new ArrayList();
        
        for (Users user : users) {
            if (user.getPosts() != null) {
                List<Post> userPosts = user.getPosts();
                for (Post post : userPosts) {
                    allPosts.add(post);
                }
            }
        }
        
        Comparator<Post> comparator = (post1, post2) -> post2.getPublicationTime().compareTo(post1.getPublicationTime()) /*Segunda maneira de implementar um Comparator*/;
        allPosts.sort(comparator);
        return allPosts;
    }
}
