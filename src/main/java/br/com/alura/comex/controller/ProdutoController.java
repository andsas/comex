package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.produto.ProdutoSaveDto;
import br.com.alura.comex.controller.dto.produto.ProdutoUpdateDto;
import br.com.alura.comex.controller.mapper.ProdutoMapper;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Produto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Optional<Produto> produto = service.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(produto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoSaveDto produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(mapper.dtoToEntity(produtoDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @Valid @RequestBody ProdutoUpdateDto produtoDto) {
        Optional<Produto> produto = service.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(mapper.dtoToEntity(id, produtoDto)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Long id) {
        Optional<Produto> produto = service.findById(id);
        if (produto.isPresent()) {
            service.delete(produto.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
