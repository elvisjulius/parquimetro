package com.parquimetro.util;

public enum TipoCobranca {
    FIXA,
    VARIAVEL;

    public String getDescricao() {
        return this.name(); // Retorna a representação em maiúsculas da enumeração como String
    }

    public static TipoCobranca fromString(String tipoCobranca) {
        for (TipoCobranca tc : TipoCobranca.values()) {
            if (tc.getDescricao().equalsIgnoreCase(tipoCobranca)) {
                return tc;
            }
        }
        throw new IllegalArgumentException("Tipo de cobrança inválido: " + tipoCobranca);
    }
}