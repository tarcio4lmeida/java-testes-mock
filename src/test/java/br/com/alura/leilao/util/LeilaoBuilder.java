package br.com.alura.leilao.util;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuilder {

    private String nome;
    private BigDecimal valorInicial;
    private Usuario usuario;
    private LocalDate data;


    public  LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder comValorInicial(String s) {
        this.valorInicial = new BigDecimal(s);
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario u) {
        this.usuario = u;
        return this;
    }

    public  LeilaoBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public Leilao criar(){
        return new Leilao(nome, valorInicial, data, usuario);
    }
}
