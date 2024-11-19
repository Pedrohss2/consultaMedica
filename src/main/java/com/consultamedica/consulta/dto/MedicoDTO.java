package com.consultamedica.consulta.dto;

import com.consultamedica.consulta.domain.entity.Medico;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MedicoDTO {

    private Long id;

    private String nome;

    private String crm;

    private String especialidade;

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
