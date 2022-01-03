package com.projeto.helpdesk.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.helpdesk.helpdesk.domain.dtos.TecnicoDTO;
import com.projeto.helpdesk.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    public Tecnico(TecnicoDTO tecnicoDto) {
        super();
        this.id = tecnicoDto.getId();
        this.nome = tecnicoDto.getNome();;
        this.cpf = tecnicoDto.getCpf();;
        this.email = tecnicoDto.getEmail();;
        this.senha = tecnicoDto.getSenha();;
        this.perfis = tecnicoDto.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = tecnicoDto.getDataCriacao();
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }
}
