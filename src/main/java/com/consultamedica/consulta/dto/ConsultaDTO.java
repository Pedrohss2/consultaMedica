package com.consultamedica.consulta.dto;

import com.consultamedica.consulta.domain.entity.Consulta;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ConsultaDTO {

    private Long id;

    private LocalDateTime horaAgendamento;

    private String local;

    private String descricao;

    private Long pacienteId;

    private Long medicoId;

    public ConsultaDTO(Consulta consulta) {
        this.id = consulta.getId();
        this.horaAgendamento = consulta.getHoraAgendamento();
        this.local = consulta.getLocal();
        this.descricao = consulta.getDescricao();
        this.pacienteId = consulta.getPaciente().getId();
        this.medicoId = consulta.getMedico().getId();
    }
}
