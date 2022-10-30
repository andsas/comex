package br.com.alura.comex.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ErroValidacaoResponse> handle(MethodArgumentNotValidException exception) {
        List<ErroValidacaoResponse> erros = new ArrayList<>();
        List<FieldError> fildErros = exception.getBindingResult().getFieldErrors();
        fildErros.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroValidacaoResponse erro = new ErroValidacaoResponse(e.getField(), mensagem);
            erros.add(erro);
        });
        return erros;
    }
}