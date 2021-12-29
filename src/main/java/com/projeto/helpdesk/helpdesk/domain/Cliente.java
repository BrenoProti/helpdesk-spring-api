package com.projeto.helpdesk.helpdesk.domain;

import com.projeto.helpdesk.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(List<Chamado> chamados) {
        super();
        this.chamados = chamados;
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
