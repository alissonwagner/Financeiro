package br.com.alisson.financeira.helper;

import br.com.alisson.financeira.model.Cliente;
import br.com.alisson.financeira.model.Endereco;
import br.com.alisson.financeira.to.ClienteTo;
import br.com.alisson.financeira.to.EnderecoTo;

import java.util.ArrayList;
import java.util.List;

public class ClienteHelper {

    public static List<ClienteTo> toClienteTo(List<Cliente> clientes) {
        List<ClienteTo> retonro = new ArrayList<>();
        clientes.forEach(cliente
                -> retonro.add(toClienteTo(cliente))
        );

        return retonro;
    }

    public static ClienteTo toClienteTo(Cliente cliente) {
        EnderecoTo enderecoTo = null;

        Endereco endereco = cliente.getEndereco();

        if (endereco != null) {
            enderecoTo = new EnderecoTo();
            enderecoTo.setCep(endereco.getCep());
            enderecoTo.setCidade(endereco.getCidade());
            enderecoTo.setComplemento(endereco.getComplemento());
            enderecoTo.setEstado(endereco.getEstado());
            enderecoTo.setId(endereco.getId());
            enderecoTo.setLogradouro(endereco.getLogradouro());
            enderecoTo.setNumero(endereco.getNumero());
            enderecoTo.setReferencia(endereco.getReferencia());
        }

        ClienteTo clienteTo = new ClienteTo();
        clienteTo.setId(cliente.getId());
        clienteTo.setNome(cliente.getNome());
        clienteTo.setRendimentoMensal(cliente.getRendimentoMensal());
        clienteTo.setEndereco(enderecoTo);

        return clienteTo;
    }
}
