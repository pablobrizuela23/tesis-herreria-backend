package com.unlar.herrecor.controlador;

import com.unlar.herrecor.modelo.LoginRequest;
import com.unlar.herrecor.modelo.Usuario;
import com.unlar.herrecor.servicio.AuthService;
import com.unlar.herrecor.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("herrecor-app/api/auth")

@CrossOrigin(origins = "*") // Permite CORS para tu frontend
public class AuthController {
    private final AuthService authService;
    @Autowired
    private UsuarioService usuarioService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario u = authService.authenticate(usuario.getEmail(), usuario.getPassword());
        if(u != null){
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}
