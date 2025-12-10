package com.f1.padroes.comportamentais;

/**
 * Interface para estratégias de corrida
 */
public interface EstrategiaCorrida {

    String executar();

    String getNome();

    double calcularBonusDesempenho();
}
