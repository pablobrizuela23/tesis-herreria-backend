package com.unlar.herrecor.servicio;

import com.unlar.herrecor.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioServicio {
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorId(Integer id);
    Usuario guardarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
    Optional<Usuario> buscarUsuarioPorEmail(String email);
}
