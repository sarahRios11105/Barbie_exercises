package main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuardaRoupaTest {

    private GuardaRoupa guardaRoupa;

    @BeforeEach
    public void setUp() {
        guardaRoupa = new GuardaRoupa(500.00);
    }

    // TESTES DO MÉTODO precoComDesconto

    @Test
    public void deveCalcularDescontoCorretamente() {
        double resultado = guardaRoupa.precoComDesconto(100.00, 20.0);

        assertEquals(80.00, resultado, 0.001);
    }

    @Test
    public void deveAceitarDescontoZero() {
        double resultado = guardaRoupa.precoComDesconto(100.00, 0.0);

        assertEquals(100.00, resultado, 0.001);
    }

    @Test
    public void deveAceitarDescontoDeCemPorCento() {
        double resultado = guardaRoupa.precoComDesconto(100.00, 100.0);

        assertEquals(0.00, resultado, 0.001);
    }

    @Test
    public void deveLancarErroParaDescontoNegativo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> guardaRoupa.precoComDesconto(100.00, -10.0)
        );
    }

    @Test
    public void deveLancarErroParaDescontoAcimaDeCem() {
        assertThrows(
                IllegalArgumentException.class,
                () -> guardaRoupa.precoComDesconto(100.00, 150.0)
        );
    }

    // TESTES DO MÉTODO cabeNoOrcamento

    @Test
    public void deveAceitarPrecoAbaixoDoOrcamento() {
        assertTrue(guardaRoupa.cabeNoOrcamento(400.00));
    }

    @Test
    public void deveAceitarPrecoIgualAoOrcamento() {
        assertTrue(guardaRoupa.cabeNoOrcamento(500.00));
    }

    @Test
    public void deveRejeitarPrecoAcimaDoOrcamento() {
        assertFalse(guardaRoupa.cabeNoOrcamento(500.01));
    }

    // TESTES DO MÉTODO ehDeGala

    @Test
    public void naoDeveSerGalaAbaixoDeDuzentos() {
        assertFalse(guardaRoupa.ehDeGala(199.99));
    }

    @Test
    public void naoDeveSerGalaQuandoPrecoForDuzentos() {
        assertFalse(guardaRoupa.ehDeGala(200.00));
    }

    @Test
    public void deveSerGalaAcimaDeDuzentos() {
        assertTrue(guardaRoupa.ehDeGala(200.01));
    }

    // TESTES DO MÉTODO registrarCompra

    @Test
    public void deveDescontarCompraDoOrcamento() {
        guardaRoupa.registrarCompra(120.00);

        assertEquals(380.00, guardaRoupa.getOrcamento(), 0.001);
    }

    // TESTES DO MÉTODO getOrcamento

    @Test
    public void deveRetornarOrcamentoInicial() {
        assertEquals(500.00, guardaRoupa.getOrcamento(), 0.001);
    }
}