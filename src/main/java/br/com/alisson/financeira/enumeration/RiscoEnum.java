package br.com.alisson.financeira.enumeration;

import java.math.BigDecimal;

public enum RiscoEnum {
    A(BigDecimal.valueOf(1.9)),
    B(BigDecimal.valueOf(5)),
    C(BigDecimal.valueOf(10));

    private BigDecimal juro;

    RiscoEnum(BigDecimal juro) {
        this.juro = juro;
    }

    public BigDecimal getJuro() {
        return juro;
    }
}
