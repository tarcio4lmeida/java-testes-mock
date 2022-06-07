package br.com.alura.leilao.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Pagamento;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class GeradorDePagamentoTest {

    private GeradorDePagamento service;

    @Mock
    private PagamentoDao pagamentoDao;

    @Captor
    private ArgumentCaptor<Pagamento> captor;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.service = new GeradorDePagamento(pagamentoDao);
    }


    @Test
    void deveriaCriarPagamentoParaVencedorDoLeilao(){
        Leilao leilao = getLeilao();
        Lance lanceVencedor = leilao.getLances().get(0);
        service.gerarPagamento(lanceVencedor);

        Mockito.verify(pagamentoDao).salvar(captor.capture());
        Pagamento pagamento = captor.getValue();

        assertEquals(LocalDate.now().plusDays(1), pagamento.getVencimento());
        assertEquals(lanceVencedor.getValor(), pagamento.getValor());
        assertFalse(pagamento.getPago());
        assertEquals(lanceVencedor.getUsuario(), pagamento.getUsuario());
        assertEquals(lanceVencedor.getLeilao(), pagamento.getLeilao());
    }

    private Leilao getLeilao() {
        Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));
        Lance primeiro = new Lance(new Usuario("Beltrano"), new BigDecimal("600"));

        leilao.propoe(primeiro);

        return leilao;
    }
}