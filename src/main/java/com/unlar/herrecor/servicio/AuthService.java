package com.unlar.herrecor.servicio;

import com.unlar.herrecor.modelo.Usuario;
import com.unlar.herrecor.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UsuarioRepositorio usuarioRepositorio;


    public AuthService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario authenticate(String email, String password) {
        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByEmail(email);

        if(usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Comparar contraseñas (si usas bcrypt o hash, usar matches)
            if(usuario.getPassword().equals(password)) {
                return usuario; // Login exitoso
            }
        }

        return null; // Credenciales incorrectas
    }
}
