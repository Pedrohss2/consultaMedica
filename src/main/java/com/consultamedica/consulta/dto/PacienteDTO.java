package com.consultamedica.consulta.dto;

import com.consultamedica.consulta.domain.entity.Paciente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
public class PacienteDTO {

    private Long id;

    @Size(min = 2, max = 50, message = "Campo nome precisar ter entre 2 a 50 caracteres")
    @NotBlank(message = "Campo nome não pode ser vazio/null")
    private String nome;

    @NotBlank(message = "Campo 'CPF' não pode ser vazio/null ")
    private String cpf;

    @NotBlank(message = "Campo 'email' não pode ser vazio/null ")
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
