package br.com.alura.comex.service.serviceImpl;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Override
    public Page<Produto> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public void delete(Produto produto) {
        repository.delete(produto);
    }
}
