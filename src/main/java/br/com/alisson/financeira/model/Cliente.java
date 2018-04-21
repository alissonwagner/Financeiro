package br.com.alisson.financeira.model;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private BigDecimal rendimentoMensal;
    //Por se tratar de uma aplicação financeira, acredito que seja mais correto apenas um endereço
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente", fetch = FetchType.EAGER, orphanRemoval = true)
    private Endereco endereco;
}
