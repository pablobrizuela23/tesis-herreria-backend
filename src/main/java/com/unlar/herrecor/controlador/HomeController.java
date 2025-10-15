package com.unlar.herrecor.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Endpoint raíz — Render lo usa para verificar que el servidor esté activo
    @GetMapping("/")
    public String home() {
        return "Servidor Herrecor activo ✅";
    }

    // Endpoint de salud — útil para mantener vivo el backend con UptimeRobot
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
