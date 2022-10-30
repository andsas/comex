package br.com.alura.comex.controller.mapper;

import br.com.alura.comex.controller.dto.cliente.ClienteSaveDto;
import br.com.alura.comex.controller.dto.cliente.ClienteUpdateDto;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ClienteService service;

    public ClienteSaveDto entityToDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteSaveDto.class);
    }

    public Cliente dtoToEntity(ClienteSaveDto dto) throws ParseException {
        return modelMapper.map(dto, Cliente.class);
    }
    public Cliente dtoToEntity(Long id, ClienteUpdateDto dto) throws ParseException {
        Optional<Cliente> cliente = service.findById(id);
        if (cliente.isPresent()) {
            cliente.get().setNome(dto.getNome());
            cliente.get().setCpf(dto.getCpf());
            cliente.get().setTelefone(dto.getTelefone());
            cliente.get().setRua(dto.getRua());
            cliente.get().setCpf(dto.getCpf());
            cliente.get().setNumero(dto.getNumero());
            cliente.get().setComplemento(dto.getComplemento());
            cliente.get().setBairro(dto.getBairro());
            cliente.get().setCidade(dto.getCidade());
            cliente.get().setEstado(dto.getEstado());
            return cliente.get();
        }
        return null;
    }
}
