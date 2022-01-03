package com.projeto.helpdesk.helpdesk.repositories;

import com.projeto.helpdesk.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
