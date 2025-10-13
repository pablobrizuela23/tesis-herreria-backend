package com.unlar.herrecor.servicio;

import com.unlar.herrecor.modelo.Proveedor;
import com.unlar.herrecor.modelo.Turno;

import java.time.LocalDateTime;
import java.util.List;

public interface ITurnoServicio {
    public List<Turno> listarTurnos();
    public Turno buscarTurnoPorId(Integer idTurno);
    public Turno guardarTurno(Turno turno);
    public void eliminarTurno(Turno turno);
    public boolean turnoDisponible(LocalDateTime fecha);




}
