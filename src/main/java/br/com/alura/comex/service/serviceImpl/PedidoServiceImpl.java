package br.com.alura.comex.service.serviceImpl;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.PedidoRepository;
import br.com.alura.comex.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository repository;

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    @Override
    public void delete(Pedido pedido) {
        repository.delete(pedido);
    }
}
