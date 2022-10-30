package br.com.alura.comex.controller.dto.cliente;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteUpdateDto {

    @NotNull @NotEmpty @Length(min = 5)
    private String nome;

    @NotNull @NotEmpty @CPF
    private String cpf;

    private String telefone;

    @NotNull @NotEmpty @Length(min = 5)
    private String rua;

    @NotNull @NotEmpty
    private String numero;

    private String complemento;

    @NotNull @NotEmpty @Length(min = 5)
    private String bairro;

    @NotNull @NotEmpty @Length(min = 5)
    private String cidade;

    @NotNull @NotEmpty @Length(min = 5)
    private String estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
