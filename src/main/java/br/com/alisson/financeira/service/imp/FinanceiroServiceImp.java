package br.com.alisson.financeira.service.imp;

import br.com.alisson.financeira.bo.ParcelaBo;
import br.com.alisson.financeira.service.FinanceiroService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class FinanceiroServiceImp implements FinanceiroService {

    @Override
    public ParcelaBo calculaParcelamento(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros) {
        ParcelaBo retorno;

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

    private static ParcelaBo calculaParcelamentoSemJuros(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros) {
        valorOriginal = valorOriginal.setScale(2, RoundingMode.HALF_UP);
        return new ParcelaBo(parcelas,
                valorOriginal.divide(BigDecimal.valueOf(parcelas), RoundingMode.HALF_UP),
                valorOriginal,
                valorOriginal);
    }

    private static ParcelaBo calculaParcelamentoComJuros(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros) {
        BigDecimal jurosDecimal = juros.divide(BigDecimal.valueOf(100));

        BigDecimal coeficienteJuros = jurosDecimal
                .divide(BigDecimal.ONE
                        .subtract(BigDecimal.ONE
                                .add(jurosDecimal)
                                .pow(parcelas * -1, MathContext.DECIMAL32)), 20, RoundingMode.HALF_UP);

        BigDecimal valorParcela = valorOriginal.multiply(coeficienteJuros).setScale(2, RoundingMode.HALF_UP);
        BigDecimal valorTotal = valorParcela.multiply(BigDecimal.valueOf(parcelas)).setScale(2);

        return new ParcelaBo(parcelas, valorParcela, valorTotal, valorOriginal.setScale(2));
    }
}
