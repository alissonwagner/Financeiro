package br.com.alisson.financeira.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data @AllArgsConstructor
public class ParcelaBo {
    private Integer parcela;
    private BigDecimal valorParcela;
    private BigDecimal valorTotal;
    private BigDecimal valorOriginal;
}
