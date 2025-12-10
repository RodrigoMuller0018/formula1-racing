package com.f1.padroes.comportamentais;

/**
 * Observador Concreto - Mídia
 */
public class MidiaObserver implements EventoObserver {
    private String canal;

    public MidiaObserver(String canal) {
        this.canal = canal;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[" + canal + "] FLASH: " + mensagem);
    }

    @Override
    public String getNome() {
        return "Mídia - " + canal;
    }
}
