package br.com.alura.comex.controller.mapper;

import br.com.alura.comex.controller.dto.categoria.CategoriaSaveDto;
import br.com.alura.comex.controller.dto.categoria.CategoriaUpdateDto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoriaMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    CategoriaService service;

    public CategoriaSaveDto entityToDto(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaSaveDto.class);
    }

    public Categoria dtoToEntity(CategoriaSaveDto dto) throws ParseException {
        return modelMapper.map(dto, Categoria.class);
    }
    public Categoria dtoToEntity(Long id, CategoriaUpdateDto dto) throws ParseException {
        Optional<Categoria> categoria = service.findById(id);
        if (categoria.isPresent()) {
            categoria.get().setNome(dto.getNome());
            return categoria.get();
        }
        return null;
    }
}
