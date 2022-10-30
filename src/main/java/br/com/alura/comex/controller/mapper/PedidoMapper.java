package br.com.alura.comex.controller.mapper;

import br.com.alura.comex.controller.dto.pedido.PedidoSaveDto;
import br.com.alura.comex.controller.dto.pedido.PedidoUpdateDto;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.enun.TipoDesconto;
import br.com.alura.comex.service.ClienteService;
import br.com.alura.comex.service.PedidoService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PedidoMapper {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    public PedidoSaveDto entityToDto(Pedido pedido) {
        return modelMapper.map(pedido, PedidoSaveDto.class);
    }

    public Pedido dtoToEntity(PedidoSaveDto dto) throws ParseException {
        Optional<Cliente> cliente = clienteService.findById(Long.parseLong(dto.getCliente()));
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente.get());
        pedido.setDesconto(new BigDecimal(dto.getDesconto()));
        pedido.setTipoDesconto(TipoDesconto.valueOf(dto.getTipoDesconto()));
        return pedido;
    }
    public Pedido dtoToEntity(Long id, PedidoUpdateDto dto) throws ParseException {
        Optional<Pedido> pedido = pedidoService.findById(id);
        if (pedido.isPresent()) {
            pedido.get().setDesconto(new BigDecimal(dto.getDesconto()));
            pedido.get().setTipoDesconto(TipoDesconto.valueOf(dto.getTipoDesconto()));
            return pedido.get();
        }
        return pedido.get();
    }
}
