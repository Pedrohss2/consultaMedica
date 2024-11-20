package com.consultamedica.consulta.dto;

import com.consultamedica.consulta.domain.entity.Medico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MedicoDTO {

    private Long id;

    @Size(min = 2, max = 50, message = "Campo nome precisar ter entre 2 a 50 caracteres")
    @NotBlank(message = "Campo nome não pode ser vazio/null")
    private String nome;

    @NotBlank(message = "Campo 'crm' não pode ser vazio/null")
    private String crm;

    @NotBlank(message = "Campo 'especialidade' não pode ser vazio/null")
    private String especialidade;

    @NotBlank(message = "Campo 'email' não pode ser vazio/null")
    private String email;

    private Integer telefone;

    public MedicoDTO(Medico medico) {
        this.id = medico.getId();
        this.nome = medico.getNome();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.telefone = medico.getTelefone();
        this.email = medico.getEmail();
    }
}
