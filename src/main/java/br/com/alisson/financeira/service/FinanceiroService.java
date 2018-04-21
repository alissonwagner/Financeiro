package br.com.alisson.financeira.service;

import br.com.alisson.financeira.bo.ParcelaBo;
import br.com.alisson.financeira.enumeration.RiscoEnum;

import java.math.BigDecimal;

public interface FinanceiroService {

    RiscoEnum getFaixaRiscoCliente(BigDecimal rendimento);

    ParcelaBo calculaParcelamento(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros);
}
