/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.blogpessoal.service;

import br.com.blogpessoal.model.Role;

/**
 *
 * @author Pedro
 */
public interface RoleService {
    Role findByName(String name);
}
