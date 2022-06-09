package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private EntityManager em;

    private UsuarioDao dao;

    @BeforeEach
    private void setup() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void finaliza(){
        em.getTransaction().rollback();
    }

    @Test
    void devebuscarPorUserName(){
        Usuario fulano = getUsuario();
        em.persist(fulano);
        Usuario encontrado = this.dao.buscarPorUsername("fulano");

        assertNotNull(fulano);
        assertEquals(fulano.getNome(), encontrado.getNome());
    }

    @Test
    void deveDeletarUmUsuario(){
        Usuario fulano = getUsuario();
        dao.deletar(fulano);

        assertThrows(NoResultException.class,
                () ->this.dao.buscarPorUsername("fulano"));

    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoEncontrado(){
        getUsuario();

        assertThrows(NoResultException.class,
                () ->this.dao.buscarPorUsername("beltrano"));

    }

    private Usuario getUsuario() {
        Usuario fulano = new Usuario("fulano", "fulano@email.com", "123456789");
        em.persist(fulano);
        return fulano;
    }

}