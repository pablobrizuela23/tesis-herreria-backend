package com.unlar.herrecor.servicio;

import com.unlar.herrecor.modelo.Turno;
import com.unlar.herrecor.repositorio.TurnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurnoServicio implements ITurnoServicio{
    @Autowired
    private TurnoRepositorio turnoRepositorio;

    @Override
    public List<Turno> listarTurnos() {
        return turnoRepositorio.findAll();
    }

    @Override
    public Turno buscarTurnoPorId(Integer idTurno) {
        Turno turno = turnoRepositorio.findById(idTurno).orElse(null);

        return turno;
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepositorio.save(turno);
    }

    @Override
    public void eliminarTurno(Turno turno) {
        turnoRepositorio.delete(turno);
    }

    @Override
    public boolean turnoDisponible(LocalDateTime fecha) {
        LocalDateTime inicio = fecha;
        LocalDateTime fin = fecha.plusMinutes(1); // ventana de 1 minuto
        return !turnoRepositorio.existsByFechaBetween(inicio, fin);
    }
}
