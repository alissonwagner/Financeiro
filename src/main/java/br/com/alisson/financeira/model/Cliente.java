package br.com.alisson.financeira.model;

import br.com.alisson.financeira.enumeration.RiscoEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private BigDecimal rendimentoMensal;
    //Por se tratar de uma aplicação financeira, acredito que seja mais correto apenas um endereço
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Endereco endereco;
}
