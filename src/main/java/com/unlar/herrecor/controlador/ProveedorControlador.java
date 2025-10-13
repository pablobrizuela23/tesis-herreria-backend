package com.unlar.herrecor.controlador;

import com.unlar.herrecor.excepcion.RecursoNoEncontradoExcepcion;
import com.unlar.herrecor.modelo.Cliente;
import com.unlar.herrecor.modelo.Proveedor;
import com.unlar.herrecor.servicio.ClienteServicio;
import com.unlar.herrecor.servicio.ProveedorServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/herrecor-app/
@RequestMapping("herrecor-app")
@CrossOrigin(value = "http://localhost:3000")
public class ProveedorControlador {

    private static final Logger logger=
            LoggerFactory.getLogger(ProveedorControlador.class);

    @Autowired
    private ProveedorServicio proveedorServicio;

    //http://localhost:8080/herrecor-app/proveedor
    @GetMapping("/proveedor")
    public List<Proveedor> obtenerProveedores(){
        var proveedores = proveedorServicio.listarProveedores();
        proveedores.forEach((proveedor -> logger.info(proveedor.toString())));
        return proveedores;
    }
    //http://localhost:8080/herrecor-app/proveedor
    @PostMapping("/proveedor")
    public Proveedor agregarProveedor(@RequestBody Proveedor proveedor){
        logger.info("Proveedor a agregar: " + proveedor);
        return proveedorServicio.guardarProveedor(proveedor);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Integer id){
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(id);
        if(proveedor==null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el proveedor id: " + id);
        }else{
            return ResponseEntity.ok(proveedor);
        }

    }

    @PutMapping("/proveedor/{id}")
    public ResponseEntity<Proveedor>
    actualizarProveedor(@PathVariable Integer id,
                      @RequestBody Proveedor proveedorRecibido){
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(id);

        if (proveedor==null){
            throw new RecursoNoEncontradoExcepcion("Id no existente "+id);
        }else{
            proveedor.setNombre(proveedorRecibido.getNombre());
            proveedor.setApellido(proveedorRecibido.getApellido());
            proveedor.setTelefono(proveedorRecibido.getTelefono());
            proveedor.setDireccion(proveedorRecibido.getDireccion());
            proveedorServicio.guardarProveedor(proveedor);
            return ResponseEntity.ok(proveedor);
        }
    }

    @DeleteMapping("/proveedor/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Integer id){
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(id);

        if (proveedor==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con ID: " + id);

        }
        proveedorServicio.eliminarProveedor(proveedor);
        return ResponseEntity.ok("Poveedor eliminado correctamente");

    }
}
