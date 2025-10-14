package com.unlar.herrecor.controlador;

import com.unlar.herrecor.excepcion.RecursoNoEncontradoExcepcion;
import com.unlar.herrecor.modelo.Cliente;
import com.unlar.herrecor.modelo.Proveedor;
import com.unlar.herrecor.servicio.ClienteServicio;
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
@CrossOrigin(value = "http://localhost:3000 , https://pablobrizuela23.github.io/tesis-herreria")
public class ClienteControlador {

    private static final Logger logger=
        LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    private ClienteServicio clienteServicio;

    //http://localhost:8080/herrecor-app/clientes
    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes(){
        var clientes = clienteServicio.listarEmpleados();
        clientes.forEach((cliente -> logger.info(cliente.toString())));
        return clientes;
    }
    //http://localhost:8080/herrecor-app/clientes
    @PostMapping("/clientes")
    public Cliente agregarCliente(@RequestBody Cliente cliente){
        logger.info("Cliente a agregar: " + cliente);
        return clienteServicio.guardarCliente(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id){
        Cliente cliente = clienteServicio.buscarClientePorId(id);
        if(cliente==null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el cliente id: " + id);
        }else{
            return ResponseEntity.ok(cliente);
        }

    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente>
        actualizarCliente(@PathVariable Integer id,
                           @RequestBody Cliente clienteRecibido){
        Cliente cliente = clienteServicio.buscarClientePorId(id);

        if (cliente==null){
            throw new RecursoNoEncontradoExcepcion("Id no existente "+id);
        }else{
            cliente.setNombre(clienteRecibido.getNombre());
            cliente.setApellido(clienteRecibido.getApellido());
            cliente.setTelefono(clienteRecibido.getTelefono());
            cliente.setDireccion(clienteRecibido.getDireccion());
            clienteServicio.guardarCliente(cliente);
            return ResponseEntity.ok(cliente);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Integer id){
        Cliente cliente = clienteServicio.buscarClientePorId(id);

        if (cliente==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente no encontrado con ID: " + id);

        }
        clienteServicio.eliminarCliente(cliente);
        return ResponseEntity.ok("Cliente eliminado correctamente");

    }
}
