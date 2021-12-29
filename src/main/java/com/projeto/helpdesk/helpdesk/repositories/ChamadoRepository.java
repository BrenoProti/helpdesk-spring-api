package com.projeto.helpdesk.helpdesk.repositories;

import com.projeto.helpdesk.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
