package br.com.alisson.financeira.service.imp;

import br.com.alisson.financeira.to.SimulacaoEmprestimoTo;
import br.com.alisson.financeira.enumeration.RiscoEnum;
import br.com.alisson.financeira.service.FinanceiroService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class FinanceiroServiceImp implements FinanceiroService {

    /**
     * Se o rendimento mensal for menor ou igual a 2000, o risco deverá ser C.
     * Se o rendimento mensal for maior que 2000 e menor ou igual a 8000, o
     * risco deverá ser B. Se o rendimento mensal for maior que 8000, o risco
     * deverá ser A.
     *
     * @param rendimento 
     * @return Enum de risico
     */
    @Override
    public RiscoEnum getFaixaRiscoCliente(BigDecimal rendimento) {
        RiscoEnum retorno;

        if (rendimento.compareTo(BigDecimal.valueOf(2000)) <= 0) {
            retorno = RiscoEnum.C;
        } else if (rendimento.compareTo(BigDecimal.valueOf(8000)) <= 0) {
            retorno = RiscoEnum.B;
        } else {
            retorno = RiscoEnum.A;
        }

        return retorno;
    }

    @Override
    public SimulacaoEmprestimoTo calculaParcelamento(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros) {
        SimulacaoEmprestimoTo retorno;

        /*
        * Não é usado equals por considerar a escala.
        * Exemplo:
        * new BigDecimal("0").equals(BigDecimal.ZERO) // true
        * new BigDecimal("0.00").equals(BigDecimal.ZERO) // false!
        * */
        if (juros.compareTo(BigDecimal.ZERO) == 0) {
            retorno = calculaParcelamentoSemJuros(valorOriginal, parcelas, juros);
        } else {
            retorno = calculaParcelamentoComJuros(valorOriginal, parcelas, juros);
        }

        return retorno;
    }

    private static SimulacaoEmprestimoTo calculaParcelamentoSemJuros(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros) {
        valorOriginal = valorOriginal.setScale(2, RoundingMode.HALF_UP);
        return new SimulacaoEmprestimoTo(parcelas,
                valorOriginal.divide(BigDecimal.valueOf(parcelas), RoundingMode.HALF_UP),
                valorOriginal,
                valorOriginal,
                BigDecimal.ZERO);
    }

    private static SimulacaoEmprestimoTo calculaParcelamentoComJuros(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros) {
        BigDecimal jurosDecimal = juros.divide(BigDecimal.valueOf(100));

        BigDecimal coeficienteJuros = jurosDecimal
                .divide(BigDecimal.ONE
                        .subtract(BigDecimal.ONE
                                .add(jurosDecimal)
                                .pow(parcelas * -1, MathContext.DECIMAL32)), 20, RoundingMode.HALF_UP);

        BigDecimal valorParcela = valorOriginal.multiply(coeficienteJuros).setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorTotal = valorParcela.multiply(BigDecimal.valueOf(parcelas)).setScale(2);

        return new SimulacaoEmprestimoTo(parcelas, valorParcela, valorTotal, valorOriginal.setScale(2), null);
    }
}
