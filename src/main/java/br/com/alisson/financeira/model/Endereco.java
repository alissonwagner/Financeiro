package br.com.alisson.financeira.model;

import java.io.Serializable;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clienteid", referencedColumnName = "ID")
    private Cliente cliente;
    private String logradouro;
    private Integer numero;
    private Long cep;
    private String cidade;
    private String estado;
    private String complemento;
    private String referencia;
}
