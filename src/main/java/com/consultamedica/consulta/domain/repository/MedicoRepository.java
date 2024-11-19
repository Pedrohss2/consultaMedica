package com.consultamedica.consulta.domain.repository;

import com.consultamedica.consulta.domain.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
