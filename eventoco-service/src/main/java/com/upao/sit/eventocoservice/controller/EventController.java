package com.upao.sit.eventocoservice.controller;

public class EventController {
}
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable Long usuarioId,
                                               @RequestBody String contraseña) {
        usuarioService.eliminarCuenta(usuarioId, contraseña);
        return ResponseEntity.noContent().build();
    }
}
