package com.f1.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Lista de pilotos da temporada 2025 da Fórmula 1
 */
public class PilotosF1_2025 {

    private static final String[][] PILOTOS_2025 = {
            { "Lando Norris", "4", "Reino Unido", "McLaren" },
            { "Max Verstappen", "1", "Holanda", "Red Bull Racing" },
            { "Oscar Piastri", "81", "Austrália", "McLaren" },
            { "George Russell", "63", "Reino Unido", "Mercedes" },
            { "Charles Leclerc", "16", "Mônaco", "Ferrari" },
            { "Lewis Hamilton", "44", "Reino Unido", "Ferrari" },
            { "Kimi Antonelli", "12", "Itália", "Mercedes" },
            { "Alexander Albon", "23", "Tailândia", "Williams" },
            { "Carlos Sainz", "55", "Espanha", "Williams" },
            { "Fernando Alonso", "14", "Espanha", "Aston Martin" },
            { "Nico Hulkenberg", "27", "Alemanha", "Sauber" },
            { "Isack Hadjar", "6", "França", "RB" },
            { "Oliver Bearman", "87", "Reino Unido", "Haas" },
            { "Liam Lawson", "30", "Nova Zelândia", "RB" },
            { "Esteban Ocon", "31", "França", "Haas" },
            { "Lance Stroll", "18", "Canadá", "Aston Martin" },
            { "Yuki Tsunoda", "22", "Japão", "Red Bull Racing" },
            { "Pierre Gasly", "10", "França", "Alpine" },
            { "Gabriel Bortoleto", "5", "Brasil", "Sauber" },
            { "Franco Colapinto", "43", "Argentina", "Alpine" },
            { "Jack Doohan", "7", "Austrália", "Alpine" }
    };

    private static List<Integer> pilotosJaCadastrados = new ArrayList<>();
    private static Random random = new Random();

    /**
     * Retorna a lista completa de pilotos disponíveis
     */
    public static List<String[]> getPilotosDisponiveis() {
        List<String[]> disponiveis = new ArrayList<>();
        for (int i = 0; i < PILOTOS_2025.length; i++) {
            if (!pilotosJaCadastrados.contains(i)) {
                disponiveis.add(PILOTOS_2025[i]);
            }
        }
        return disponiveis;
    }

    /**
     * Cadastra N pilotos aleatórios da lista 2025
     * 
     * @param quantidade Número de pilotos a cadastrar
     * @return Lista de pilotos cadastrados
     */
    public static List<Piloto> cadastrarPilotosAleatorios(int quantidade) {
        List<Piloto> pilotos = new ArrayList<>();
        List<Integer> indicesDisponiveis = new ArrayList<>();

        // Encontrar índices disponíveis
        for (int i = 0; i < PILOTOS_2025.length; i++) {
            if (!pilotosJaCadastrados.contains(i)) {
                indicesDisponiveis.add(i);
            }
        }

        // Verificar se há pilotos suficientes disponíveis
        if (indicesDisponiveis.isEmpty()) {
            System.out.println("Todos os pilotos ja foram cadastrados!");
            return pilotos;
        }

        // Limitar quantidade aos disponíveis
        int qtdReal = Math.min(quantidade, indicesDisponiveis.size());

        // Embaralhar para pegar aleatoriamente
        Collections.shuffle(indicesDisponiveis, random);

        // Cadastrar pilotos
        for (int i = 0; i < qtdReal; i++) {
            int indice = indicesDisponiveis.get(i);
            String[] dadosPiloto = PILOTOS_2025[indice];

            Piloto piloto = new Piloto(
                    dadosPiloto[0], // nome
                    Integer.parseInt(dadosPiloto[1]), // numero
                    dadosPiloto[2] // nacionalidade
            );
            piloto.setEquipe(dadosPiloto[3]);

            pilotos.add(piloto);
            pilotosJaCadastrados.add(indice);
        }

        return pilotos;
    }

    /**
     * Retorna quantidade de pilotos ainda disponíveis
     */
    public static int getPilotosRestantes() {
        return PILOTOS_2025.length - pilotosJaCadastrados.size();
    }

    /**
     * Reseta a lista de pilotos cadastrados
     */
    public static void resetar() {
        pilotosJaCadastrados.clear();
    }

    /**
     * Exibe lista de pilotos disponíveis
     */
    public static void exibirPilotosDisponiveis() {
        System.out.println("\nPILOTOS DISPONIVEIS PARA CADASTRO:");
        System.out.println("=".repeat(60));

        List<String[]> disponiveis = getPilotosDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum piloto disponivel!");
        } else {
            for (String[] piloto : disponiveis) {
                System.out.printf("#%-3s %-25s (%s) - %s\n",
                        piloto[1], piloto[0], piloto[2], piloto[3]);
            }
            System.out.println("\nTotal disponivel: " + disponiveis.size() + " pilotos");
        }
    }
}
