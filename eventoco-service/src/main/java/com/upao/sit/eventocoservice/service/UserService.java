package com.upao.sit.eventocoservice.service;

public class UserService {
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

    @Transactional
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

        usuarioRepository.delete(usuario);
    }
}
