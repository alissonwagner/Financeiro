package br.com.alisson.financeira.to;

import lombok.Data;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteTo {

    private Long id;
    private String nome;
    private BigDecimal rendimentoMensal;
    private EnderecoTo endereco;
    private String risco;
}
