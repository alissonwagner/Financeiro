package br.com.alisson.financeira;

import br.com.alisson.financeira.to.SimulacaoEmprestimoTo;
import br.com.alisson.financeira.enumeration.RiscoEnum;
import br.com.alisson.financeira.service.FinanceiroService;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FinanceiraApplication.class)
public class FinanceiraApplicationTests {

    @Autowired
    private FinanceiroService financeiroService;

    @Test
    public void testarCalculoComJuros() {
        SimulacaoEmprestimoTo esperado = new SimulacaoEmprestimoTo(12, BigDecimal.valueOf(916.80).setScale(2), BigDecimal.valueOf(11001.60).setScale(2), BigDecimal.valueOf(10000).setScale(2));
        SimulacaoEmprestimoTo resultado = financeiroService.calculaParcelamento(BigDecimal.valueOf(10000), 12, BigDecimal.valueOf(1.5));
        Assert.assertEquals(esperado, resultado);
    }

    @Test
    public void testarCalculoSemJuros() {
        SimulacaoEmprestimoTo esperado = new SimulacaoEmprestimoTo(12, BigDecimal.valueOf(833.33).setScale(2), BigDecimal.valueOf(10000).setScale(2), BigDecimal.valueOf(10000).setScale(2));
        SimulacaoEmprestimoTo resultado = financeiroService.calculaParcelamento(BigDecimal.valueOf(10000), 12, BigDecimal.ZERO);
        Assert.assertEquals(esperado, resultado);
    }

    @Test
    public void testarFaixaRiscoA() {
        RiscoEnum risco = financeiroService.getFaixaRiscoCliente(BigDecimal.valueOf(1950.93));
        Assert.assertEquals(RiscoEnum.C, risco);
    }

    @Test
    public void testarFaixaRiscoB() {
        RiscoEnum risco = financeiroService.getFaixaRiscoCliente(BigDecimal.valueOf(3509.09));
        Assert.assertEquals(RiscoEnum.B, risco);
    }

    @Test
    public void testarFaixaRiscoC() {
        RiscoEnum risco = financeiroService.getFaixaRiscoCliente(BigDecimal.valueOf(10000));
        Assert.assertEquals(RiscoEnum.A, risco);
    }

}
