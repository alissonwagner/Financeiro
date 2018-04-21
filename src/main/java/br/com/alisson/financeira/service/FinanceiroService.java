package br.com.alisson.financeira.service;

import br.com.alisson.financeira.to.SimulacaoEmprestimoTo;
import br.com.alisson.financeira.enumeration.RiscoEnum;

import java.math.BigDecimal;

public interface FinanceiroService {

    RiscoEnum getFaixaRiscoCliente(BigDecimal rendimento);

    SimulacaoEmprestimoTo calculaParcelamento(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros);
}
