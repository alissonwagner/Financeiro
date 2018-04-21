package br.com.alisson.financeira.to;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SimulacaoEmprestimoTo {

    private Integer parcelas;
    private BigDecimal valorParcela;
    private BigDecimal valorTotal;
    private BigDecimal valorOriginal;
    private BigDecimal taxaDeJuros;
}
