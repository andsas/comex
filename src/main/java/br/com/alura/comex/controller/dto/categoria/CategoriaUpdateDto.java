package br.com.alura.comex.controller.dto.categoria;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaUpdateDto {

    @NotNull @NotEmpty @Length(min = 5)
    private String nome;

    @NotNull @NotEmpty @Length(min = 5)
    private String status;

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }
}
