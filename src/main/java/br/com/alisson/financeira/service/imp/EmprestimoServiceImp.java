package br.com.alisson.financeira.service.imp;

import br.com.alisson.financeira.enumeration.RiscoEnum;
import br.com.alisson.financeira.exception.ResourceNotFoundException;
import br.com.alisson.financeira.model.Cliente;
import br.com.alisson.financeira.service.ClienteService;
import br.com.alisson.financeira.service.EmprestimoService;
import br.com.alisson.financeira.service.FinanceiroService;
import br.com.alisson.financeira.to.RequisicaoEmprestimoTo;
import br.com.alisson.financeira.to.SimulacaoEmprestimoTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImp implements EmprestimoService {

    private final FinanceiroService financeiroService;
    private final ClienteService clienteService;

    @Override
    public SimulacaoEmprestimoTo simulaEmprestimo(RequisicaoEmprestimoTo requisicaoEmprestimoTo) {
        Cliente cliente = clienteService.getCliente(requisicaoEmprestimoTo.getCliente());

        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        RiscoEnum faixaRiscoCliente = financeiroService.getFaixaRiscoCliente(cliente.getRendimentoMensal());

        SimulacaoEmprestimoTo resultado = financeiroService.calculaParcelamento(requisicaoEmprestimoTo.getValor(), requisicaoEmprestimoTo.getPrestacoes(), faixaRiscoCliente.getJuro());
        resultado.setTaxaDeJuros(faixaRiscoCliente.getJuro());
        return resultado;
    }

}
