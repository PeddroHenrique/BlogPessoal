/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.model;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Pedro
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class Users {
    
    @Id
    private String id;
    private String username;
    private String password;
    private List<Role> roles;
    private Profile profile;
    private String avatarFile;
    private LocalDateTime registrationDate;
    private List<Post> posts;
    private List<String> friendsId;
    
    public String getAvatarFile() {
        return Base64.getEncoder().encodeToString(profile.getAvatar());
    }
}
