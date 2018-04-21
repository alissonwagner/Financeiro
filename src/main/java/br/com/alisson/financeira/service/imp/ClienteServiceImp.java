package br.com.alisson.financeira.service.imp;

import br.com.alisson.financeira.exception.ResourceNotFoundException;
import br.com.alisson.financeira.model.Cliente;
import br.com.alisson.financeira.model.Endereco;
import br.com.alisson.financeira.repository.ClienteRepository;
import br.com.alisson.financeira.service.ClienteService;
import br.com.alisson.financeira.to.ClienteTo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getTodosClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getCliente(Long codigo) {
        return clienteRepository.findOne(codigo);
    }

    @Override
    public Cliente insertCliente(ClienteTo clienteTo) {
        Cliente clienteModel = new Cliente();
        clienteModel.setNome(clienteTo.getNome());
        clienteModel.setRendimentoMensal(clienteTo.getRendimentoMensal());

        Endereco enderecoModel = new Endereco();
        enderecoModel.setCep(clienteTo.getEndereco().getCep());
        enderecoModel.setCidade(clienteTo.getEndereco().getCidade());
        enderecoModel.setComplemento(clienteTo.getEndereco().getComplemento());
        enderecoModel.setEstado(clienteTo.getEndereco().getEstado());
        enderecoModel.setLogradouro(clienteTo.getEndereco().getLogradouro());
        enderecoModel.setNumero(clienteTo.getEndereco().getNumero());
        enderecoModel.setReferencia(clienteTo.getEndereco().getReferencia());
        enderecoModel.setCliente(clienteModel);

        clienteModel.setEndereco(enderecoModel);

        clienteRepository.save(clienteModel);

        return clienteModel;
    }

    @Override
    public Cliente updateCliente(ClienteTo clienteTo) {
        Cliente clienteModel = clienteRepository.findOne(clienteTo.getId());

        if (clienteModel == null) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        } else {
            clienteModel.setNome(clienteTo.getNome());
            clienteModel.setRendimentoMensal(clienteTo.getRendimentoMensal());

            Endereco enderecoModel = null;

            if (clienteTo.getEndereco() != null) {
                enderecoModel = new Endereco();
                enderecoModel.setId(clienteTo.getEndereco().getId());
                enderecoModel.setCep(clienteTo.getEndereco().getCep());
                enderecoModel.setCidade(clienteTo.getEndereco().getCidade());
                enderecoModel.setComplemento(clienteTo.getEndereco().getComplemento());
                enderecoModel.setEstado(clienteTo.getEndereco().getEstado());
                enderecoModel.setLogradouro(clienteTo.getEndereco().getLogradouro());
                enderecoModel.setNumero(clienteTo.getEndereco().getNumero());
                enderecoModel.setReferencia(clienteTo.getEndereco().getReferencia());
                enderecoModel.setCliente(clienteModel);
            }

            clienteModel.setEndereco(enderecoModel);

            clienteRepository.save(clienteModel);
        }

        return clienteModel;
    }

    @Override
    public void apagarCliente(Long codigo) {
        Cliente cliente = clienteRepository.findOne(codigo);

        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        clienteRepository.delete(cliente);
    }
}
