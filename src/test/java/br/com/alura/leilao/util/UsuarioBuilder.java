package br.com.alura.leilao.util;

import br.com.alura.leilao.model.Usuario;

public class UsuarioBuilder {

    private String nome;

    private String senha;

    private String email;

    public  UsuarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder comSenha(String s) {
        this.senha = s;
        return this;
    }

    public UsuarioBuilder comEmail(String s) {
        this.email = s;
        return this;
    }

    public Usuario criar(){
        return new Usuario(nome, email, senha);
    }

}
