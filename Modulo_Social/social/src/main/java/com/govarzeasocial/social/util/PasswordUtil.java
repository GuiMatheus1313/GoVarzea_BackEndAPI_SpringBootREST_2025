package com.govarzeasocial.social.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    //Codificar a senha
    public static String encodeSenha(String rawSenha){
        return encoder.encode(rawSenha);
    }

    //Verificação de match entre banco e web
    public static boolean matches(String rawSenha, String encodedSenha){
        return encoder.matches(rawSenha, encodedSenha);
    }
}
