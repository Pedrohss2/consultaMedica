package com.consultamedica.consulta.services;

import com.consultamedica.consulta.domain.entity.Medico;
import com.consultamedica.consulta.domain.repository.MedicoRepository;
import com.consultamedica.consulta.dto.MedicoDTO;
import com.consultamedica.consulta.services.Exceptions.BancoDeDadosException;
import com.consultamedica.consulta.services.Exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MedicoDTO findById(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Medico não encontrado!"));

        return new MedicoDTO(medico);
    }

    public MedicoDTO create(MedicoDTO dto) {
        Medico medico = modelMapper.map(dto, Medico.class);

        medico.setCriadoEm(Date.from(Instant.now()));
        medico.setAtualizadoEm(Date.from(Instant.now()));

        medico = medicoRepository.save(medico);

        return  modelMapper.map(medico, MedicoDTO.class);
    }

    public MedicoDTO update(Long id, MedicoDTO dto) {
        try {
            Medico medico = modelMapper.map(dto, Medico.class);

            medico.setCriadoEm(Date.from(Instant.now()));
            medico.setAtualizadoEm(Date.from(Instant.now()));

            medicoRepository.save(medico);

            return  modelMapper.map(medico, MedicoDTO.class);

        } catch (EntityNotFoundException error) {
            throw new RecursoNaoEncontradoException("Paciente não encontrado");
        }
    }

    public void delete(Long id) {
        medicoRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Medico não encontrado"));

        try {
            medicoRepository.deleteById(id);

        }catch (DataIntegrityViolationException error) {
            throw new BancoDeDadosException("Erro ao deletar o paciente ");
        }
    }
}
