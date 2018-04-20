package br.com.alisson.financeira.repository;

import br.com.alisson.financeira.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
