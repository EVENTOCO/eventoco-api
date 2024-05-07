package com.upao.sit.eventocoservice.service;

public class UserService {
    
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void eliminarCuenta(Long usuarioId, String contraseña) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contraseña, usuario.getContraseña())) {
            throw new InvalidPasswordException("La contraseña ingresada es incorrecta");
        }

        usuarioRepository.delete(usuario);
    }
}
