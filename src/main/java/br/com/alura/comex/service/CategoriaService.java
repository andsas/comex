package br.com.alura.comex.service;

import br.com.alura.comex.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CategoriaService {

    Page<Categoria> findAll(Pageable pageable);
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    void delete(Categoria categoria);

}
