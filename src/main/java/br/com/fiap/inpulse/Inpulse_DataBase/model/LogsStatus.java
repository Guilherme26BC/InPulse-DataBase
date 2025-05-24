package br.com.fiap.inpulse.Inpulse_DataBase.model;

public enum LogsStatus {
    enviada("Enviada"),
    erro("Erro"),
    EnviadaComSucesso("Enviada com sucesso"),
    cancelado("Cancelado"),
    auditado("Auditando"),
    aguardandoAprovação("Aguardando aprovação "),
    aprovado("Aprovada");

    LogsStatus(String mensagem) {
    this.mensagem = mensagem;
    }
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }
}
