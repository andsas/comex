package br.com.alura.comex.service;

import br.com.alura.comex.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProdutoService {

    Page<Produto> findAll(Pageable pageable);
    Optional<Produto> findById(Long id);
    Produto save(Produto produto);
    void delete(Produto produto);

}
