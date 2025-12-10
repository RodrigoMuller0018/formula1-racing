package com.f1.modelo;

/**
 * Classe que representa um piloto de Fórmula 1
 */
public class Piloto {
    private String nome;
    private int numero;
    private String nacionalidade;
    private int vitorias;
    private int pontos;
    private String equipe;

    public Piloto(String nome, int numero, String nacionalidade) {
        this.nome = nome;
        this.numero = numero;
        this.nacionalidade = nacionalidade;
        this.vitorias = 0;
        this.pontos = 0;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void adicionarVitoria() {
        this.vitorias++;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void resetarPontos() {
        this.pontos = 0;
        this.vitorias = 0;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return String.format("Piloto #%d - %s (%s) | Equipe: %s | Vitórias: %d | Pontos: %d",
                numero, nome, nacionalidade, equipe != null ? equipe : "Sem equipe",
                vitorias, pontos);
    }
}
