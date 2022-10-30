package br.com.alura.comex.controller.dto.pedido;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PedidoSaveDto {

    @NotNull @NotEmpty
    private String data;

    @NotNull @NotEmpty
    private String cliente;

    @NotNull @NotEmpty
    private String desconto;

    @NotNull @NotEmpty
    private String tipoDesconto;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }
}
