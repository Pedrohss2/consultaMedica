package com.consultamedica.consulta.services;

import com.consultamedica.consulta.domain.entity.Paciente;
import com.consultamedica.consulta.domain.repository.PacienteRepository;
import com.consultamedica.consulta.dto.PacienteDTO;
import com.consultamedica.consulta.services.Exceptions.BancoDeDadosException;
import com.consultamedica.consulta.services.Exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public PacienteDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pacinte não encontrado"));

        return new PacienteDTO(paciente);
    }

    public PacienteDTO create(PacienteDTO dto) {
        Paciente paciente = modelMapper.map(dto, Paciente.class);

        paciente.setCriadoEm(Date.from(Instant.now()));
        paciente.setAtualizadoEm(Date.from(Instant.now()));

        paciente = pacienteRepository.save(paciente);

        return  modelMapper.map(paciente, PacienteDTO.class);
    }

    public PacienteDTO update(Long id, PacienteDTO dto) {
        try {
            Paciente paciente = modelMapper.map(dto, Paciente.class);

            paciente.setCriadoEm(Date.from(Instant.now()));
            paciente.setAtualizadoEm(Date.from(Instant.now()));

               pacienteRepository.save(paciente);

            return  modelMapper.map(paciente, PacienteDTO.class);

        } catch (EntityNotFoundException error) {
            throw new RecursoNaoEncontradoException("Paciente não encontrado");
        }
    }

    public void delete(Long id) {
        pacienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pacinte não encontrado"));

        try {
            pacienteRepository.deleteById(id);

        }catch (DataIntegrityViolationException error) {
            throw new BancoDeDadosException("Erro ao deletar o paciente - DataIntegrityViolationException");
        }
    }

}
