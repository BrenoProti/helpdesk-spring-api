package com.projeto.helpdesk.helpdesk.repositories;

import com.projeto.helpdesk.helpdesk.domain.Cliente;
import com.projeto.helpdesk.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
