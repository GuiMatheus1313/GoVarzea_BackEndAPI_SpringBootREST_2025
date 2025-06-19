package com.govarzeasocial.social.configuration.springSecurity;

import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.model.enums.Role;
import com.govarzeasocial.social.repository.PessoaRepo;
import com.govarzeasocial.social.service.PessoaService;
import com.govarzeasocial.social.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//É O FILTRO DEFINIDO
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Autowired
    private PessoaRepo pessoaRepo;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.startsWith("/auth") || path.startsWith("/h2-console")) {
            filterChain.doFilter(request, response); // pula o filtro
            return;
        }
        System.out.println("doFilterInternal()");

        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwtToken = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwtToken = authorizationHeader.substring(7);
            try{
                email = jwtUtil.extractUsername(jwtToken);
            } catch (Exception e){
                System.out.println("Token inválido ou expirado: " + e.getMessage());
            }
        }
        
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            try{
                Claims claim = jwtUtil.extractClaims(jwtToken);
                String role = claim.get("role", String.class);
                System.out.println("Esse é meu role: " + role);

                if(role != null){
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
                    System.out.println("Lista de Authorities" + authorities.toString());
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(email, "", authorities);

                    if(jwtUtil.validateToken(jwtToken, userDetails)){
                        System.out.println("validateToken(-)");
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } else {
                    System.out.println("Tokem sem role!");
                }
            } catch (Exception e){
                System.out.println("Erro ao extrair claims: " + e.getMessage());
            }

        }
        filterChain.doFilter(request, response);
    }
}
