package br.ufes.enumeracoes;

public enum SituacaoPedido {
    PAGO("PAGO"), PENDENTE("PENDENTE"), CANCELADO("CANCELADO"), VENCIDO("VENCIDO");

    private final String estado;

    SituacaoPedido(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
