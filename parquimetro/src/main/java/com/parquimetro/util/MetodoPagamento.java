package com.parquimetro.util;

public enum MetodoPagamento {
    CREDITO,
    DEBITO,
    PIX;

    public String getDescricao() {
        return this.name();
    }

    public static MetodoPagamento fromString(String metodoPagamento) {
        for (MetodoPagamento mp : MetodoPagamento.values()) {
            if (mp.getDescricao().equalsIgnoreCase(metodoPagamento)) {
                return mp;
            }
        }
        throw new IllegalArgumentException("Método de pagamento inválido: " + metodoPagamento);
    }
}