package com.consultamedica.consulta.controller;

import com.consultamedica.consulta.dto.ConsultaDTO;
import com.consultamedica.consulta.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/{id}")
    private ResponseEntity<ConsultaDTO> findById(@PathVariable Long id) {
        ConsultaDTO consutaDTO = consultaService.findById(id);

        return ResponseEntity.ok().body(consutaDTO);
    }

    @PostMapping
    private ResponseEntity<ConsultaDTO> create(@Valid @RequestBody ConsultaDTO dto) {
        dto = consultaService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<ConsultaDTO> update(@PathVariable Long id, @Valid @RequestBody ConsultaDTO dto) {
        dto = consultaService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
