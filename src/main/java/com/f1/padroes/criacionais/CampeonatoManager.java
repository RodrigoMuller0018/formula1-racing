package com.f1.padroes.criacionais;

import com.f1.modelo.CarroF1;
import com.f1.modelo.Piloto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Singleton para gerenciar o campeonato de Fórmula 1
 */
public class CampeonatoManager {

    // Instância única e privada
    private static CampeonatoManager instance;

    // Estado do campeonato
    private List<CarroF1> carrosInscritos;
    private String temporada;
    private int corridasRealizadas;
    private List<String> historicoVencedores;
    private CarroF1 vencedorUltimaCorrida;

    /**
     * Construtor PRIVADO - impede instanciação externa
     */
    private CampeonatoManager() {
        this.carrosInscritos = new ArrayList<>();
        this.historicoVencedores = new ArrayList<>();
        this.temporada = "2025";
        this.corridasRealizadas = 0;
        System.out.println("CampeonatoManager inicializado (Singleton)");
    }

    /**
     * Método público estático para obter a instância única
     * Thread-safe com synchronized
     */
    public static synchronized CampeonatoManager getInstance() {
        if (instance == null) {
            instance = new CampeonatoManager();
        }
        return instance;
    }

    public void inscreverCarro(CarroF1 carro) {
        carrosInscritos.add(carro);
        System.out.println("Carro inscrito: " + carro.getEscuderia() +
                " - Piloto: " + carro.getPiloto().getNome());
    }

    public void simularCorrida(String nomeCorrida) {
        if (carrosInscritos.isEmpty()) {
            System.out.println("Nenhum carro inscrito no campeonato!");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("CORRIDA: " + nomeCorrida);
        System.out.println("=".repeat(70));

        // Classificação aleatória
        List<CarroF1> classificacao = new ArrayList<>(carrosInscritos);
        Collections.shuffle(classificacao, new Random());

        // Sistema de pontuação F1 (25, 18, 15, 12, 10, 8, 6, 4, 2, 1)
        int[] pontos = { 25, 18, 15, 12, 10, 8, 6, 4, 2, 1 };

        System.out.println("\nRESULTADO DA CORRIDA:");
        System.out.println("─".repeat(70));

        for (int i = 0; i < Math.min(classificacao.size(), pontos.length); i++) {
            CarroF1 carro = classificacao.get(i);
            Piloto piloto = carro.getPiloto();

            piloto.adicionarPontos(pontos[i]);
            if (i == 0) {
                piloto.adicionarVitoria();
                historicoVencedores.add(piloto.getNome() + " - " + nomeCorrida);
                vencedorUltimaCorrida = carro; // Armazena o vencedor
            }

            String posicao = i == 0 ? "[1]" : i == 1 ? "[2]" : i == 2 ? "[3]" : "   ";
            System.out.printf("%s %2d° - %-20s (%s) - %2d pontos\n",
                    posicao, i + 1, piloto.getNome(), carro.getEscuderia(),
                    pontos[i]);
        }

        corridasRealizadas++;
        System.out.println("=".repeat(70));
    }

    public void exibirClassificacaoCampeonato() {
        if (carrosInscritos.isEmpty()) {
            System.out.println("Nenhum carro inscrito no campeonato!");
            return;
        }

        System.out.println("\n" + "═".repeat(70));
        System.out.println("CLASSIFICAÇÃO DO CAMPEONATO - TEMPORADA " + temporada);
        System.out.println("Corridas realizadas: " + corridasRealizadas);
        System.out.println("═".repeat(70));

        List<Piloto> pilotos = new ArrayList<>();
        for (CarroF1 carro : carrosInscritos) {
            pilotos.add(carro.getPiloto());
        }

        pilotos.sort(Comparator.comparingInt(Piloto::getPontos).reversed());

        for (int i = 0; i < pilotos.size(); i++) {
            Piloto piloto = pilotos.get(i);
            String posicao = i == 0 ? "[1]" : i == 1 ? "[2]" : i == 2 ? "[3]" : "   ";
            System.out.printf("%s %2d° - %s\n", posicao, i + 1, piloto);
        }
        System.out.println("═".repeat(70));
    }

    public void exibirHistoricoVencedores() {
        System.out.println("\nHISTÓRICO DE VENCEDORES:");
        for (int i = 0; i < historicoVencedores.size(); i++) {
            System.out.println((i + 1) + ". " + historicoVencedores.get(i));
        }
    }

    public void resetarCampeonato() {
        carrosInscritos.clear();
        historicoVencedores.clear();
        corridasRealizadas = 0;
        System.out.println("Campeonato resetado!");
    }

    // Getters
    public List<CarroF1> getCarrosInscritos() {
        return new ArrayList<>(carrosInscritos);
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public int getCorridasRealizadas() {
        return corridasRealizadas;
    }

    public CarroF1 getVencedorUltimaCorrida() {
        return vencedorUltimaCorrida;
    }
}
