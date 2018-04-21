package br.com.alisson.financeira.controller;

import br.com.alisson.financeira.exception.ResourceNotFoundException;
import br.com.alisson.financeira.service.EmprestimoService;
import br.com.alisson.financeira.to.RequisicaoEmprestimoTo;
import br.com.alisson.financeira.to.SimulacaoEmprestimoTo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping(path = "/simulacao")
    public ResponseEntity simulador(@RequestBody RequisicaoEmprestimoTo requisicaoEmprestimo) {
        try {
            SimulacaoEmprestimoTo simulacaoEmprestimo = emprestimoService.simulaEmprestimo(requisicaoEmprestimo);
            return new ResponseEntity<>(simulacaoEmprestimo, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
