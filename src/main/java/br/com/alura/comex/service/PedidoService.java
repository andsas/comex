package br.com.alura.comex.service;

import br.com.alura.comex.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PedidoService {

    Page<Pedido> findAll(Pageable pageable);
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    void delete(Pedido pedido);

}
