package com.f1.padroes.comportamentais;

/**
 * Observador Concreto - Equipe
 */
public class EquipeObserver implements EventoObserver {
    private String nomeEquipe;

    public EquipeObserver(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    @Override
    public void atualizar(String mensagem) {
        System.out.println("[EQUIPE " + nomeEquipe + "] recebeu: " + mensagem);
        analisarEvento(mensagem);
    }

    private void analisarEvento(String evento) {
        if (evento.contains("pit stop")) {
            System.out.println("   → Equipe preparando estratégia de resposta...");
        } else if (evento.contains("Safety Car")) {
            System.out.println("   → Avaliando janela para pit stop...");
        }
    }

    @Override
    public String getNome() {
        return "Equipe " + nomeEquipe;
    }
}
