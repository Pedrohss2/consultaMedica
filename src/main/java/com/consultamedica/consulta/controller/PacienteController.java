package com.consultamedica.consulta.controller;

import com.consultamedica.consulta.dto.PacienteDTO;
import com.consultamedica.consulta.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{id}")
    private ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        PacienteDTO pacienteDTO = pacienteService.findById(id);

        return ResponseEntity.ok().body(pacienteDTO);
    }

    @PostMapping
    private ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO dto) {
        dto = pacienteService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<PacienteDTO> update(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
        dto = pacienteService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.ok().build();
    }

}
