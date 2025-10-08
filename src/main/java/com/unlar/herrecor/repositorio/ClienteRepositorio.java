package com.unlar.herrecor.repositorio;

import com.unlar.herrecor.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {
}
