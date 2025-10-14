package com.unlar.herrecor.controlador;

import com.unlar.herrecor.excepcion.RecursoNoEncontradoExcepcion;
import com.unlar.herrecor.modelo.Proveedor;
import com.unlar.herrecor.modelo.Turno;
import com.unlar.herrecor.servicio.ProveedorServicio;
import com.unlar.herrecor.servicio.TurnoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
//http://localhost:8080/herrecor-app/
@RequestMapping("herrecor-app")
@CrossOrigin(value = "http://localhost:3000 , https://pablobrizuela23.github.io/tesis-herreria")
public class TurnoControlador {


    private static final Logger logger=
            LoggerFactory.getLogger(ProveedorControlador.class);

    @Autowired
    private TurnoServicio turnoServicio;

    //http://localhost:8080/herrecor-app/turno
    @GetMapping("/turno")
    public List<Turno> obtenerTurno(){
        var turnos = turnoServicio.listarTurnos();
        turnos.forEach((turno -> logger.info(turno.toString())));
        return turnos;
    }
    //http://localhost:8080/herrecor-app/turno
    @PostMapping("/turno")
    public Turno agregarTurno(@RequestBody Turno turno){
        logger.info("Turno a agregar: " + turno);
        return turnoServicio.guardarTurno(turno);
    }

    @GetMapping("/turno/{id}")
    public ResponseEntity<Turno> obtenerTurnoPorId(@PathVariable Integer id){
        Turno turno = turnoServicio.buscarTurnoPorId(id);
        if(turno==null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el turno id: " + id);
        }else{
            return ResponseEntity.ok(turno);
        }

    }

    @PutMapping("/turno/{id}")
    public ResponseEntity<Turno>
    actualizarTurno(@PathVariable Integer id,
                        @RequestBody Turno turnoRecibido){
        Turno turno = turnoServicio.buscarTurnoPorId(id);

        if (turno==null){
            throw new RecursoNoEncontradoExcepcion("Id no existente "+id);
        }else{
            turno.setNombre(turnoRecibido.getNombre());
            turno.setDni(turnoRecibido.getDni());
            turno.setDireccion(turnoRecibido.getDireccion());
            turno.setTelefono(turnoRecibido.getTelefono());
            turno.setDescripcion(turnoRecibido.getDescripcion());
            turno.setFecha(turnoRecibido.getFecha());
            turnoServicio.guardarTurno(turno);
            return ResponseEntity.ok(turno);
        }
    }

    @DeleteMapping("/turno/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        Turno turno = turnoServicio.buscarTurnoPorId(id);

        if (turno==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Turno no encontrado con ID: " + id);

        }
        turnoServicio.eliminarTurno(turno);
        return ResponseEntity.ok("Turno eliminado correctamente");

    }

    @GetMapping("/turno/check")
    public boolean turnoDisponible(@RequestParam String fecha) {
        LocalDateTime fechaParseada = LocalDateTime.parse(fecha);
        return turnoServicio.turnoDisponible(fechaParseada);
    }


}
