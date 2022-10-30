package br.com.alura.comex.controller;

import br.com.alura.comex.controller.dto.cliente.ClienteSaveDto;
import br.com.alura.comex.controller.dto.cliente.ClienteUpdateDto;
import br.com.alura.comex.controller.mapper.ClienteMapper;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private ClienteMapper mapper;

    @GetMapping
    public ResponseEntity<Page<Cliente>> findAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Optional<Cliente> cliente = service.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteSaveDto clienteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(mapper.dtoToEntity(clienteDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDto clienteDto) {
        Optional<Cliente> cliente = service.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(mapper.dtoToEntity(id, clienteDto)));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Optional<Cliente> cliente = service.findById(id);
        if (cliente.isPresent()) {
            service.delete(cliente.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
