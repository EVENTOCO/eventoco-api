package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.model.entity.Coment;
import com.upao.sit.eventocoservice.repository.ComentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ComentService {
    private final ComentRepository comentRepository;

    @Transactional
    public void createComent(Coment coment) {
        comentRepository.save(coment);
    }
}
