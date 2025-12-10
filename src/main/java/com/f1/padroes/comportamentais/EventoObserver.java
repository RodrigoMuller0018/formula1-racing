package com.f1.padroes.comportamentais;

/**
 * Interface Observer para o padrão Observer
 */
public interface EventoObserver {
    void atualizar(String mensagem);

    String getNome();
}
