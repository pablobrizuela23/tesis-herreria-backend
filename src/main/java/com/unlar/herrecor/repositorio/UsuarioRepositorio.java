package com.unlar.herrecor.repositorio;

import com.unlar.herrecor.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByEmail(String email);
}
