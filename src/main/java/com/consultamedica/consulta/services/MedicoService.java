package com.consultamedica.consulta.services;

import com.consultamedica.consulta.domain.entity.Medico;
import com.consultamedica.consulta.domain.repository.MedicoRepository;
import com.consultamedica.consulta.dto.MedicoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    private MedicoDTO findById(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Medico não encontrado!"));

        return new MedicoDTO(medico);
    }

    public MedicoDTO create(MedicoDTO dto) {
        Medico medico = modelMapper.map(dto, Medico.class);

        medico.setCriadoEm(Date.from(Instant.now()));
        medico.setAtualizadoEm(Date.from(Instant.now()));

        medico = medicoRepository.save(medico);

        return  modelMapper.map(medico, MedicoDTO.class);
    }

}
