package br.com.alura.comex.controller.mapper;

import br.com.alura.comex.controller.dto.produto.ProdutoSaveDto;
import br.com.alura.comex.controller.dto.produto.ProdutoUpdateDto;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.CategoriaService;
import br.com.alura.comex.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ProdutoMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    public ProdutoSaveDto entityToDto(Produto produto) {
        return modelMapper.map(produto, ProdutoSaveDto.class);
    }

    public Produto dtoToEntity(ProdutoSaveDto dto) throws ParseException {
        Optional<Categoria> categoria = categoriaService.findById(Long.parseLong(dto.getCategoria()));
        Produto produto = new Produto();
        produto.setCategoria(categoria.get());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPrecoUnitario(new BigDecimal(dto.getPrecoUnitario().replace(",",".")));
        produto.setQuantidadeEstoque(Integer.parseInt(dto.getQuantidadeEstoque()));
        return produto;
    }
    public Produto dtoToEntity(Long id, ProdutoUpdateDto dto) throws ParseException {
        System.out.println("getPrecoUnitario");
        System.out.println(dto.getPrecoUnitario());
        Optional<Produto> produto = produtoService.findById(id);
        Optional<Categoria> categoria = categoriaService.findById(Long.parseLong(dto.getCategoria()));
        if (produto.isPresent() && categoria.isPresent()) {
            produto.get().setCategoria(categoria.get());
            produto.get().setNome(dto.getNome());
            produto.get().setDescricao(dto.getDescricao());
            produto.get().setPrecoUnitario(new BigDecimal(dto.getPrecoUnitario().replace(",",".")));
            produto.get().setQuantidadeEstoque(Integer.parseInt(dto.getQuantidadeEstoque()));
            return produto.get();
        } else {
            System.out.println("Not Found!!");
        }
        return null;
    }
}
