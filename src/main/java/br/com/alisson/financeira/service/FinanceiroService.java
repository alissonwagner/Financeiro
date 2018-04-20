package br.com.alisson.financeira.service;

import br.com.alisson.financeira.bo.ParcelaBo;

import java.math.BigDecimal;

public interface FinanceiroService {
    ParcelaBo calculaParcelamento(BigDecimal valorOriginal, Integer parcelas, BigDecimal juros);
}
