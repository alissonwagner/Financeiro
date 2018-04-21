package br.com.alisson.financeira.service;

import br.com.alisson.financeira.to.RequisicaoEmprestimoTo;
import br.com.alisson.financeira.to.SimulacaoEmprestimoTo;

public interface EmprestimoService {

    SimulacaoEmprestimoTo simulaEmprestimo(RequisicaoEmprestimoTo requisicaoEmprestimoTo);
}
