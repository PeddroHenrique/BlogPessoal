/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.blogpessoal.service.impl;

import br.com.blogpessoal.model.Role;
import br.com.blogpessoal.repository.RoleRepository;
import br.com.blogpessoal.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pedro
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository RoleRepository;
    
    @Override
    public Role findByName(String name) {
        return RoleRepository.findByName(name);
    }
    
}
