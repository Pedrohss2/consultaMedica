package com.consultamedica.consulta.services;

import com.consultamedica.consulta.domain.entity.Consulta;
import com.consultamedica.consulta.domain.repository.ConsultaRepository;
import com.consultamedica.consulta.dto.ConsultaDTO;
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
public class ConsultaService {

    @Autowired
    private ConsultaRepository consutaRepository;

    @Autowired
    private  EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    public ConsultaDTO findById(Long id) {
        Consulta consulta = consutaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Consulta não encontrada!"));

        return new ConsultaDTO(consulta);
    }

    public ConsultaDTO create(ConsultaDTO dto) {
        Consulta consulta = modelMapper.map(dto, Consulta.class);

        consulta.setCriadoEm(Date.from(Instant.now()));
        consulta.setAtualizadoEm(Date.from(Instant.now()));

        consulta = consutaRepository.save(consulta);

        return  modelMapper.map(consulta, ConsultaDTO.class);
    }

    public ConsultaDTO update(Long id, ConsultaDTO dto) {
        try {
            Consulta consulta = modelMapper.map(dto, Consulta.class);

            consulta.setCriadoEm(Date.from(Instant.now()));
            consulta.setAtualizadoEm(Date.from(Instant.now()));

            consutaRepository.save(consulta);

            return  modelMapper.map(consulta, ConsultaDTO.class);

        } catch (EntityNotFoundException error) {
            throw new RecursoNaoEncontradoException("Paciente não encontrado");
        }
    }

    public void delete(Long id) {
        consutaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Pacinte não encontrado"));

        try {
            consutaRepository.deleteById(id);

        }catch (DataIntegrityViolationException error) {
            throw new BancoDeDadosException("Erro ao deletar o paciente - DataIntegrityViolationException");
        }
    }


    private void enviarEmailParaPaciente(Consulta consulta) {
        String emailPaciente = consulta.getPaciente().getEmail();
        String assunto = "Consulta Agendada";
        String corpoEmail = String.format(
                "Olá %s,<br><br> Sua consulta com o Dr(a). %s foi agendada para %s.<br><br>Atenciosamente,<br>Equipe de Consultas",
                consulta.getPaciente().getNome(),
                consulta.getMedico().getNome(),
                consulta.getHoraAgendamento().toString()
        );
        emailService.sendEmail(emailPaciente, assunto, corpoEmail);
    }

    private void enviarEmailParaMedico(Consulta consulta) {
        String emailMedico = consulta.getMedico().getEmail();
        String assunto = "Nova Consulta Agendada";
        String corpoEmail = String.format(
                "Olá Dr(a). %s,<br><br> Uma nova consulta foi agendada com o paciente %s para %s.<br><br>Atenciosamente,<br>Equipe de Consultas",
                consulta.getMedico().getNome(),
                consulta.getPaciente().getNome(),
                consulta.getHoraAgendamento().toString()
        );
        emailService.sendEmail(emailMedico, assunto, corpoEmail);
    }
}
