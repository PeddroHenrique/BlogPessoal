/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.blogpessoal.repository;

import br.com.blogpessoal.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pedro
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
    Role findByName(String name);
}
