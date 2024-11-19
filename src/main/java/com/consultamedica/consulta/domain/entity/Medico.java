package com.consultamedica.consulta.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "crm", nullable = false)
    private String crm;

    @Column(name = "especialidade", nullable = false)
    private String especialidade;

    @Email
    @Column(unique = true, name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false)
    private Integer telefone;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criado_em", nullable = false, updatable = false)
    private Date criadoEm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "atualizado_em", nullable = false, updatable = false)
    private Date atualizadoEm;

}
