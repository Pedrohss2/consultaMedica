package com.consultamedica.consulta.dto;

import com.consultamedica.consulta.domain.entity.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
public class PacienteDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private Integer telefone;

    private LocalDate dataNascimento;

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.cpf = paciente.getCpf();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.dataNascimento = paciente.getDataNascimento();
    }
}
