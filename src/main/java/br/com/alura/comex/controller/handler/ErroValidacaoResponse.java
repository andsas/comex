package br.com.alura.comex.controller.handler;

public class ErroValidacaoResponse {

    private String campo;
    private String mensagem;

    public ErroValidacaoResponse(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
