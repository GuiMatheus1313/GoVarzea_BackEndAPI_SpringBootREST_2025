package com.govarzeasocial.social.service.security;


import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.repository.PessoaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PessoaRepo pessoaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername(-)");
        Pessoa pessoa = pessoaRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with:" + username));

        //Lidar com ROLE
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(pessoa.getTipoPerfil().name()));
        System.out.println("Perfil atual: " + pessoa.getTipoPerfil().name());
        return org.springframework.security.core.userdetails.User.builder()
                .username(pessoa.getEmail()) // usando email como identificador
                .password(pessoa.getSenha()) // senha já codificada
                .authorities(authorities) // você pode adicionar roles depois
                .accountLocked(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
