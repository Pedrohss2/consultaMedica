package com.consultamedica.consulta.domain.repository;

import com.consultamedica.consulta.domain.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
