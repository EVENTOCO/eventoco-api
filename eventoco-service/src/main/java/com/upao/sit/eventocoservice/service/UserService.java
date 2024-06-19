package com.upao.sit.eventocoservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EventoRepository eventoRepository;
    private final ComentarioRepository comentarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder, EventoRepository eventoRepository, ComentarioRepository comentarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventoRepository = eventoRepository;
        this.comentarioRepository = comentarioRepository;
    }

    public void eliminarCuenta(Long usuarioId, String contraseña) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contraseña, usuario.getContraseña())) {
            throw new InvalidPasswordException("La contraseña ingresada es incorrecta");
        }

        // Eliminar eventos creados por el usuario
        List<Evento> eventosDelUsuario = eventoRepository.findByCreador(usuario.getUsername());
        eventoRepository.deleteAll(eventosDelUsuario);

        // Eliminar comentarios del usuario
        List<Comentario> comentariosDelUsuario = comentarioRepository.findByUsuario(usuario);
        comentarioRepository.deleteAll(comentariosDelUsuario);

        // Eliminar otros datos relacionados al usuario
        // Añadir lógica para eliminar otros datos si es necesario

        usuarioRepository.delete(usuario);
    }
}
