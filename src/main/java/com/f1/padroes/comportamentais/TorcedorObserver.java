package com.f1.padroes.comportamentais;

/**
 * Observador Concreto - Torcedor
 */
public class TorcedorObserver implements EventoObserver {
    private String nomePilotoFavorito;

    public TorcedorObserver(String nomePilotoFavorito) {
        this.nomePilotoFavorito = nomePilotoFavorito;
    }

    @Override
    public void atualizar(String mensagem) {
        if (mensagem.contains(nomePilotoFavorito)) {
            System.out.println("[TORCEDOR] MEU PILOTO " + nomePilotoFavorito + "! " + mensagem);
        }
    }

    @Override
    public String getNome() {
        return "Torcedor de " + nomePilotoFavorito;
    }
}
