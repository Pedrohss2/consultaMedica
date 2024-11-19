package com.consultamedica.consulta.domain.repository;

import com.consultamedica.consulta.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
