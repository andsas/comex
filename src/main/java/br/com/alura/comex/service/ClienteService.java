package br.com.alura.comex.service;

import br.com.alura.comex.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClienteService {

    Page<Cliente> findAll(Pageable pageable);
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void delete(Cliente cliente);

}
