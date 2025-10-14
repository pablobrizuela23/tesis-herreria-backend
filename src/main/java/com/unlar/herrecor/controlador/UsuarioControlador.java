package com.unlar.herrecor.controlador;

import com.unlar.herrecor.excepcion.RecursoNoEncontradoExcepcion;
import com.unlar.herrecor.modelo.Proveedor;
import com.unlar.herrecor.modelo.Usuario;
import com.unlar.herrecor.servicio.ProveedorServicio;
import com.unlar.herrecor.servicio.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//http://localhost:8080/herrecor-app/
@RequestMapping("herrecor-app")
@CrossOrigin(value = "http://localhost:3000 , https://pablobrizuela23.github.io/tesis-herreria")
public class UsuarioControlador {
    private static final Logger logger=
            LoggerFactory.getLogger(UsuarioControlador.class);

    @Autowired
    private UsuarioService usuarioService;

    //http://localhost:8080/herrecor-app/usuario
    @GetMapping("/usuario")
    public List<Usuario> obtenerUsuario(){
        var usuarios = usuarioService.listarUsuarios();
        usuarios.forEach((usuario -> logger.info(usuario.toString())));
        return usuarios;
    }
    //http://localhost:8080/herrecor-app/usuario
    @PostMapping("/usuario")
    public Usuario agregarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario a agregar: " + usuario);
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if(usuario==null){
            throw new RecursoNoEncontradoExcepcion("No se encontro el usuario id: " + id);
        }else{
            return ResponseEntity.ok(usuario);
        }

    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario>
    actualizarUsuario(@PathVariable Integer id,
                        @RequestBody Usuario usuarioRecibido){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);

        if (usuario==null){
            throw new RecursoNoEncontradoExcepcion("Id no existente "+id);
        }else{
            usuario.setNombre(usuarioRecibido.getNombre());
            usuario.setDni(usuarioRecibido.getDni());
            usuario.setDireccion(usuarioRecibido.getDireccion());
            usuario.setTelefono(usuarioRecibido.getTelefono());
            usuario.setEmail(usuarioRecibido.getEmail());
            usuario.setPassword(usuarioRecibido.getPassword());
            usuario.setRol(usuarioRecibido.getRol());

            usuarioService.guardarUsuario(usuario);
            return ResponseEntity.ok(usuario);
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);

        if (usuario==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con ID: " + id);

        }
        usuarioService.eliminarUsuario(usuario);
        return ResponseEntity.ok("Usuario eliminado correctamente");

    }

    @GetMapping("/usuario/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(email);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
