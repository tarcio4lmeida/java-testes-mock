package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.LeilaoBuilder;
import br.com.alura.leilao.util.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeilaoDaoTest {

    private EntityManager em;

    private LeilaoDao dao;

    @BeforeEach
    private void setup() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void finaliza(){
        em.getTransaction().rollback();
    }

    @Test
    void deveriaCriarUmLeilao(){
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);

        Leilao leilaoEncontrado = dao.buscarPorId(leilao.getId());

        assertNotNull(leilaoEncontrado);
    }

    @Test
    void deveriaAtualizarUmLeilao(){

        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("12345678")
                .criar();

        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comData(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);

        leilao.setNome("Geladeira");
        leilao = dao.salvar(leilao);

        Leilao leilaoEncontrado = dao.buscarPorId(leilao.getId());

        assertNotNull(leilaoEncontrado);
        assertEquals("Geladeira", leilao.getNome());
    }

}