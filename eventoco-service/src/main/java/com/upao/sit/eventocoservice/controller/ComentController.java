package com.upao.sit.eventocoservice.controller;

import com.upao.sit.eventocoservice.model.entity.Coment;
import com.upao.sit.eventocoservice.service.ComentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/coments")
@AllArgsConstructor
public class ComentController {
    private final ComentService comentService;

    @PostMapping
    public ResponseEntity<Void> createComent(@RequestBody Coment coment) {
        comentService.createComent(coment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
