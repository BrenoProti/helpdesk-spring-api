package com.projeto.helpdesk.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.helpdesk.helpdesk.domain.dtos.ClienteDTO;
import com.projeto.helpdesk.helpdesk.domain.dtos.TecnicoDTO;
import com.projeto.helpdesk.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(List<Chamado> chamados) {
        super();
        this.chamados = chamados;
    }

    public Cliente(ClienteDTO clienteDto) {
        super();
        this.id = clienteDto.getId();
        this.nome = clienteDto.getNome();;
        this.cpf = clienteDto.getCpf();;
        this.email = clienteDto.getEmail();;
        this.senha = clienteDto.getSenha();;
        this.perfis = clienteDto.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = clienteDto.getDataCriacao();
    }

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha, Set<Perfil> perfis, List<Chamado> chamados) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }
}
