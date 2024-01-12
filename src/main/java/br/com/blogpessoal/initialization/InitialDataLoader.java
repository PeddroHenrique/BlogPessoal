/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.initialization;

import br.com.blogpessoal.model.Role;
import br.com.blogpessoal.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pedro
 */
@Component
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner{

    private final RoleRepository roleRepository;
    
    @Override
    public void run(String... args) throws Exception {
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            Role role = new Role();
            role.setName("USER");
            roleRepository.save(role);
        }

        Role adminRole = roleRepository.findByName("ADMIN");
        if (adminRole == null) {
            Role role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
        }
    }
}
