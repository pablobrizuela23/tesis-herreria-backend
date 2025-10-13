package com.unlar.herrecor.repositorio;

import com.unlar.herrecor.modelo.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TurnoRepositorio extends JpaRepository<Turno, Integer> {

    @Query("SELECT CASE WHEN COUNT(t)>0 THEN true ELSE false END FROM Turno t WHERE t.fecha BETWEEN :inicio AND :fin")
    boolean existsByFechaBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

}
