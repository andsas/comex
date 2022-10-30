package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.pedido.PedidoSaveDto;
import br.com.alura.comex.controller.dto.pedido.PedidoUpdateDto;
import br.com.alura.comex.controller.mapper.PedidoMapper;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Pedido>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Optional<Pedido> pedido = service.findById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody @Valid PedidoSaveDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(mapper.dtoToEntity(pedidoDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @Valid @RequestBody PedidoUpdateDto pedidoDto) {
        Optional<Pedido> pedido = service.findById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(mapper.dtoToEntity(id, pedidoDto)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> delete(@PathVariable Long id) {
        Optional<Pedido> pedido = service.findById(id);
        if (pedido.isPresent()) {
            service.delete(pedido.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
