package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.categoria.CategoriaSaveDto;
import br.com.alura.comex.controller.dto.categoria.CategoriaUpdateDto;
import br.com.alura.comex.controller.mapper.CategoriaMapper;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Categoria>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(categoria.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody @Valid CategoriaSaveDto categoriaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(mapper.dtoToEntity(categoriaDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @Valid @RequestBody CategoriaUpdateDto categoriaDto) {
        Optional<Categoria> categoria = service.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(mapper.dtoToEntity(id, categoriaDto)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable Long id) {
        Optional<Categoria> categoria = service.findById(id);
        if (categoria.isPresent()) {
            service.delete(categoria.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
