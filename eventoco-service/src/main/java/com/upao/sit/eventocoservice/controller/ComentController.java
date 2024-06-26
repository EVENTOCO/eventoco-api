package com.upao.sit.eventocoservice.controller;

import com.upao.sit.eventocoservice.model.dto.EventResponseDTO;
import com.upao.sit.eventocoservice.model.entity.Coment;
import com.upao.sit.eventocoservice.service.ComentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coments") // api/v1/coments -> a mi API REST
@AllArgsConstructor
public class ComentController {
    private final ComentService comentService;

    @PostMapping // HTTP
    public ResponseEntity<Void> createComent(@RequestBody Coment coment) {
        comentService.createComent(coment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
