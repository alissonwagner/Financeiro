package br.com.alisson.financeira.service;

import br.com.alisson.financeira.model.Cliente;
import br.com.alisson.financeira.to.ClienteTo;
import java.util.List;

public interface ClienteService {

    List<Cliente> getTodosClientes();

    Cliente getCliente(Long codigo);

    Cliente insertCliente(ClienteTo clienteTo);

    Cliente updateCliente(ClienteTo clienteTo);
    
    void apagarCliente(Long codigo);
}
