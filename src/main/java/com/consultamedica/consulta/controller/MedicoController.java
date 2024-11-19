package com.consultamedica.consulta.controller;

import com.consultamedica.consulta.dto.MedicoDTO;
import com.consultamedica.consulta.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping("/{id}")
    private ResponseEntity<MedicoDTO> findById(@PathVariable Long id) {
        MedicoDTO medicoDTO = medicoService.findById(id);

        return ResponseEntity.ok().body(medicoDTO);
    }

    @PostMapping
    private ResponseEntity<MedicoDTO> create(@Valid @RequestBody MedicoDTO dto) {
        dto = medicoService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<MedicoDTO> update(@PathVariable Long id, @Valid @RequestBody MedicoDTO dto) {
        dto = medicoService.update(id, dto);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
