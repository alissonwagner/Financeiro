package br.com.alisson.financeira.controller;

import br.com.alisson.financeira.helper.ClienteHelper;
import br.com.alisson.financeira.model.Cliente;
import br.com.alisson.financeira.repository.ClienteRepository;
import br.com.alisson.financeira.repository.EnderecoRepository;
import br.com.alisson.financeira.to.ClienteTo;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "cadastrocliente";
    }

    @RequestMapping(path = "/cliente/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<ClienteTo> clientes = ClienteHelper.toClienteTo(clienteRepository.findAll());
        model.addAttribute("clientes", clientes);
        return "index";
    }
//    @ResponseBody

    @RequestMapping(path = "/cliente/{codigo}", method = RequestMethod.GET)
    public String get(@PathVariable("codigo") Long codigo, Model model) {
        String retorno = "detalhecliente";
        Cliente cliente = clienteRepository.findOne(codigo);

        if (cliente == null) {
            retorno = "redirect:/";
        } else {
            model.addAttribute("cliente", ClienteHelper.toClienteTo(cliente));
        }

        return retorno;
    }

    @RequestMapping(path = "/cliente/salvar", method = RequestMethod.POST)
    public String salvar(ClienteTo cliente) {
        Cliente clienteModel = new Cliente();
        clienteModel.setId(cliente.getId());
        clienteModel.setNome(cliente.getNome());
        clienteModel.setRendimentoMensal(cliente.getRendimentoMensal());

        clienteRepository.save(clienteModel);
        System.out.println(cliente.toString());
        return "redirect:/cliente/" + cliente.getId();
    }
}
