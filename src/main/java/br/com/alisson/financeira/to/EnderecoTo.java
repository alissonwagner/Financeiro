package br.com.alisson.financeira.to;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoTo {
    private Long id;
    private String logradouro;
    private Integer numero;
    private Long cep;
    private String cidade;
    private String estado;
    private String complemento;
    private String referencia;
}
