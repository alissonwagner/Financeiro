package br.com.alisson.financeira.to;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RequisicaoEmprestimoTo  {
    private BigDecimal valor;
    private Integer prestacoes;
    private Long cliente;
}
