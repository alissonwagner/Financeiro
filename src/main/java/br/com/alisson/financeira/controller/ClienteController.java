package br.com.alisson.financeira.controller;

import br.com.alisson.financeira.exception.ResourceNotFoundException;
import br.com.alisson.financeira.helper.ClienteHelper;
import br.com.alisson.financeira.model.Cliente;
import br.com.alisson.financeira.service.ClienteService;
import br.com.alisson.financeira.service.FinanceiroService;
import br.com.alisson.financeira.to.ClienteTo;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final FinanceiroService financeiroService;

    @GetMapping("/list")
    public ResponseEntity<List<ClienteTo>> list() {
        List<ClienteTo> clientes = ClienteHelper.toClienteTo(clienteService.getTodosClientes());
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<ClienteTo> get(@PathVariable("codigo") Long codigo) {
        Cliente cliente = clienteService.getCliente(codigo);

        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            ClienteTo clienteTo = ClienteHelper.toClienteTo(cliente);
            clienteTo.setRisco(financeiroService.getFaixaRiscoCliente(cliente.getRendimentoMensal()).name());
            return new ResponseEntity<>(clienteTo, HttpStatus.OK);
        }
    }

    @PutMapping(path = "/salvar")
    public ResponseEntity<ClienteTo> alterar(@RequestBody ClienteTo cliente) {
        try {
            Cliente clienteModel = clienteService.updateCliente(cliente);
            ClienteTo clienteTo = ClienteHelper.toClienteTo(clienteModel);
            clienteTo.setRisco(financeiroService.getFaixaRiscoCliente(clienteTo.getRendimentoMensal()).name());
            return new ResponseEntity<>(clienteTo, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/salvar")
    public ResponseEntity<ClienteTo> salvar(@RequestBody ClienteTo cliente) {
        Cliente clienteModel = clienteService.insertCliente(cliente);
        ClienteTo clienteTo = ClienteHelper.toClienteTo(clienteModel);
        clienteTo.setRisco(financeiroService.getFaixaRiscoCliente(clienteTo.getRendimentoMensal()).name());
        return new ResponseEntity<>(clienteTo, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity apagar(@PathVariable("codigo") Long codigo) {
        try {
            clienteService.apagarCliente(codigo);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
