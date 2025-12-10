package com.f1.padroes.comportamentais;

/**
 * PADRÃO STRATEGY - Comportamental
 *
 * Context: Gerenciador de estratégia
 * Mantém uma referência para a estratégia atual e permite trocá-la dinamicamente
 */
public class GerenciadorEstrategia {
    private EstrategiaCorrida estrategiaAtual;
    private String nomePiloto;

    public GerenciadorEstrategia(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    /**
     * Define a estratégia a ser usada
     */
    public void definirEstrategia(EstrategiaCorrida estrategia) {
        this.estrategiaAtual = estrategia;
        System.out.println("[" + nomePiloto + "] Estratégia definida: " + estrategia.getNome());
    }

    /**
     * Executa a estratégia atual
     */
    public void executarEstrategia() {
        if (estrategiaAtual == null) {
            System.out.println("Nenhuma estratégia definida!");
            return;
        }

        System.out.println("\nExecutando estratégia: " + estrategiaAtual.getNome());
        System.out.println("─".repeat(60));
        System.out.println(estrategiaAtual.executar());
        System.out.println("Bônus de desempenho: " + estrategiaAtual.calcularBonusDesempenho());
        System.out.println("─".repeat(60));
    }

    /**
     * Retorna a estratégia atual
     */
    public EstrategiaCorrida getEstrategiaAtual() {
        return estrategiaAtual;
    }

    /**
     * Exibe todas as estratégias disponíveis
     */
    public void exibirEstrategiasDisponiveis() {
        System.out.println("\nESTRATÉGIAS DISPONÍVEIS:");
        System.out.println("1. Uma Parada - Conservadora e eficiente");
        System.out.println("2. Duas Paradas - Balanceada com pneus frescos");
        System.out.println("3. Agressiva - Máximo ataque desde o início");
    }
}
