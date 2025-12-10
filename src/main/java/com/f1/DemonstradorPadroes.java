package com.f1;

import com.f1.modelo.CarroF1;
import com.f1.modelo.Piloto;
import com.f1.padroes.comportamentais.*;
import com.f1.padroes.criacionais.*;
import com.f1.padroes.estruturais.*;

/**
 * Classe que demonstra automaticamente todos os padrões de projeto implementados
 */
public class DemonstradorPadroes {

    public static void executarDemonstracao() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║        DEMONSTRACAO COMPLETA DOS PADROES DE PROJETO              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");

        demonstrarSingleton();
        pausar();

        demonstrarBuilder();
        pausar();

        demonstrarAdapter();
        pausar();

        demonstrarStrategy();
        pausar();

        demonstrarObserver();
        pausar();

        demonstrarIntegracao();
    }

    private static void demonstrarSingleton() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("[1] PADRAO SINGLETON - Criacional");
        System.out.println("=".repeat(70));
        System.out.println("Proposito: Garantir que uma classe tenha apenas uma instancia\n");

        System.out.println("Criando instâncias do CampeonatoManager...");
        CampeonatoManager manager1 = CampeonatoManager.getInstance();
        CampeonatoManager manager2 = CampeonatoManager.getInstance();

        System.out.println("manager1 == manager2? " + (manager1 == manager2));
        System.out.println("Singleton funciona! Ambas as referências apontam para a mesma instância.");
        System.out.println("Hash manager1: " + manager1.hashCode());
        System.out.println("Hash manager2: " + manager2.hashCode());
    }

    private static void demonstrarBuilder() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("[2] PADRAO BUILDER - Criacional");
        System.out.println("=".repeat(70));
        System.out.println("Proposito: Separar a construcao de um objeto complexo\n");

        System.out.println("Criando carro usando Builder com method chaining...\n");

        Piloto verstappen = new Piloto("Max Verstappen", 1, "Holanda");

        CarroF1 carro = new CarroF1Builder()
                .comEscuderia("Red Bull Racing")
                .comMotor("Honda RBPT")
                .comChassi("RB19")
                .comPotencia(1000)
                .comAerodinamica(95.5)
                .comPeso(798.0)
                .comPiloto(verstappen)
                .build();

        System.out.println("Carro construído com sucesso usando Builder!");
        System.out.println(carro);
    }

    private static void demonstrarAdapter() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("[3] PADRAO ADAPTER - Estrutural");
        System.out.println("=".repeat(70));
        System.out.println("Proposito: Converter interface de uma classe em outra\n");

        Piloto norris = new Piloto("Lando Norris", 4, "Reino Unido");
        CarroF1 carro = new CarroF1Builder()
                .comEscuderia("McLaren F1 Team")
                .comMotor("Mercedes M14")
                .comChassi("MCL60")
                .comPotencia(980)
                .comAerodinamica(91.0)
                .comPeso(798.0)
                .comPiloto(norris)
                .build();

        System.out.println("Adaptando CarroF1 para SistemaTelemetria...\n");

        TelemetriaAdapter telemetria = new TelemetriaAdapter(carro);

        System.out.println("Telemetria adaptada com sucesso:");
        System.out.println(telemetria.getTelemetriaCompleta());
    }

    private static void demonstrarStrategy() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("[4] PADRAO STRATEGY - Comportamental");
        System.out.println("=".repeat(70));
        System.out.println("Proposito: Definir familia de algoritmos intercambiaveis\n");

        Piloto piloto = new Piloto("Sergio Perez", 11, "México");
        GerenciadorEstrategia gerenciador = new GerenciadorEstrategia(piloto.getNome());

        System.out.println("Testando diferentes estratégias...\n");

        // Estratégia 1: Uma Parada
        gerenciador.definirEstrategia(new EstrategiaUmaParada());
        gerenciador.executarEstrategia();

        System.out.println("\n" + "-".repeat(70) + "\n");

        // Estratégia 2: Duas Paradas
        gerenciador.definirEstrategia(new EstrategiaDuasParadas());
        gerenciador.executarEstrategia();

        System.out.println("\n" + "-".repeat(70) + "\n");

        // Estratégia 3: Agressiva
        gerenciador.definirEstrategia(new EstrategiaAgressiva());
        gerenciador.executarEstrategia();

        System.out.println("\nEstratégias trocadas dinamicamente com sucesso!");
    }

    private static void demonstrarObserver() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("[5] PADRAO OBSERVER - Comportamental");
        System.out.println("=".repeat(70));
        System.out.println("Proposito: Notificar multiplos observadores sobre mudancas\n");

        SistemaNotificacoes sistema = new SistemaNotificacoes();

        System.out.println("Registrando observadores...\n");

        sistema.adicionarObservador(new EquipeObserver("Red Bull Racing"));
        sistema.adicionarObservador(new MidiaObserver("ESPN"));
        sistema.adicionarObservador(new MidiaObserver("F1TV"));
        sistema.adicionarObservador(new TorcedorObserver("Max Verstappen"));

        System.out.println("\nNotificando evento para todos os observadores...\n");

        sistema.notificarEvento("Max Verstappen fez pit stop na volta 15!");

        System.out.println("\n" + "-".repeat(70) + "\n");

        sistema.notificarEvento("Safety Car deployed! Oportunidade de pit stop!");
    }

    private static void demonstrarIntegracao() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("[6] INTEGRACAO DE TODOS OS PADROES");
        System.out.println("=".repeat(70));
        System.out.println("Demonstrando como todos os padroes trabalham juntos\n");

        // Singleton
        CampeonatoManager campeonato = CampeonatoManager.getInstance();

        // Builder
        Piloto p1 = new Piloto("Max Verstappen", 1, "Holanda");
        Piloto p2 = new Piloto("Charles Leclerc", 16, "Mônaco");
        Piloto p3 = new Piloto("Lewis Hamilton", 44, "Reino Unido");

        CarroF1 carro1 = new CarroF1Builder()
                .comEscuderia("Red Bull Racing")
                .comMotor("Honda RBPT")
                .comChassi("RB19")
                .comPotencia(1000)
                .comAerodinamica(95.5)
                .comPeso(798.0)
                .comPiloto(p1)
                .build();

        CarroF1 carro2 = new CarroF1Builder()
                .comEscuderia("Scuderia Ferrari")
                .comMotor("Ferrari V6 Turbo")
                .comChassi("SF-23")
                .comPotencia(990)
                .comAerodinamica(93.0)
                .comPeso(798.0)
                .comPiloto(p2)
                .build();

        CarroF1 carro3 = new CarroF1Builder()
                .comEscuderia("Mercedes-AMG Petronas")
                .comMotor("Mercedes M14 E Performance")
                .comChassi("W14")
                .comPotencia(985)
                .comAerodinamica(92.0)
                .comPeso(798.0)
                .comPiloto(p3)
                .build();

        // Observer
        SistemaNotificacoes sistema = new SistemaNotificacoes();
        sistema.adicionarObservador(new MidiaObserver("F1 Broadcast"));

        // Singleton - Inscrever carros
        campeonato.inscreverCarro(carro1);
        campeonato.inscreverCarro(carro2);
        campeonato.inscreverCarro(carro3);

        // Observer - Notificar início
        sistema.notificarEvento("Iniciando GP de Demonstracao!");

        // Singleton - Simular corrida
        campeonato.simularCorrida("GP de Demonstração - Todos os Padrões");

        // Singleton - Exibir classificação
        campeonato.exibirClassificacaoCampeonato();

        System.out.println("\nDEMONSTRACAO COMPLETA FINALIZADA!");
        System.out.println("Todos os 5 padroes foram demonstrados com sucesso:");
        System.out.println("  1. Singleton (Criacional)");
        System.out.println("  2. Builder (Criacional)");
        System.out.println("  3. Adapter (Estrutural)");
        System.out.println("  4. Strategy (Comportamental)");
        System.out.println("  5. Observer (Comportamental)");
    }

    private static void pausar() {
        System.out.println("\n[Aguarde...]");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
