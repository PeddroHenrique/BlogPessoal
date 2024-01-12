/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.blogpessoal.repository;

import br.com.blogpessoal.model.Post;
import br.com.blogpessoal.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pedro
 */
@Repository
public interface UsersRepository extends MongoRepository<Users, String> {

    @Query("{username:'?0'}")
    Users findByUsername(String username);
    
}
