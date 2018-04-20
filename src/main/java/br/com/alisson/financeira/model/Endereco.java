package br.com.alisson.financeira.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "clienteid", referencedColumnName = "id")
    private Cliente cliente;
    private String logradouro;
    private Integer numero;
    private Long cep;
    private String cidade;
    private String estado;
    private String complemento;
    private String referencia;
}
