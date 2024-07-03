package com.upao.sit.eventocoservice.service;


import com.upao.sit.eventocoservice.model.entity.Coment;
import com.upao.sit.eventocoservice.repository.ComentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComentServiceTest {
    @Mock
    private ComentRepository comentRepository;
    @InjectMocks
    private ComentService comentService;

    // test/enviar-comentarios
    @Test
    public void testCreateComent(){
        //Arrange
        Coment coment = new Coment();
        coment.setUser_id(String.valueOf(1));
        coment.setEvent_id(String.valueOf(1));
        coment.setDescription("Comentario de prueba");

        when(comentRepository.save(coment)).thenReturn(coment);

        //Act
        comentService.createComent(coment);

        //Assert
        assertNotNull(coment);

    }
}
