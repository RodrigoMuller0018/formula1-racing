package com.f1;

import com.f1.modelo.CarroF1;
import com.f1.modelo.Piloto;
import com.f1.modelo.PilotosF1_2025;
import com.f1.padroes.comportamentais.*;
import com.f1.padroes.criacionais.*;
import com.f1.padroes.estruturais.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CampeonatoManager campeonato = CampeonatoManager.getInstance();
    private static SistemaNotificacoes sistemaNotificacoes = new SistemaNotificacoes();

    public static void main(String[] args) {
        exibirBanner();

        while (true) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    criarCarro();
                    break;
                case 2:
                    cadastrarPilotosAleatorios();
                    break;
                case 3:
                    definirEstrategia();
                    break;
                case 4:
                    visualizarTelemetria();
                    break;
                case 5:
                    configurarObservadores();
                    break;
                case 6:
                    simularCorrida();
                    break;
                case 7:
                    verClassificacao();
                    break;
                case 8:
                    demonstracaoCompleta();
                    break;
                case 0:
                    System.out.println("\nEncerrando sistema F1. Ate logo!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcao invalida!");
            }

            pausar();
        }
    }

    private static void exibirBanner() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         SISTEMA F1 - 2025                         ║");
        System.out.println("║               Sistema de Gerenciamento de Formula 1               ║");
        System.out.println("║                Demonstracao de Padroes de Projeto                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void exibirMenu() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         MENU PRINCIPAL                            ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Criar Carro Manualmente (Builder)                             ║");
        System.out.println("║  2. Cadastrar Pilotos Aleatorios 2025                             ║");
        System.out.println("║  3. Definir Estrategia de Corrida (Strategy)                      ║");
        System.out.println("║  4. Visualizar Telemetria (Adapter)                               ║");
        System.out.println("║  5. Configurar Observadores (Observer)                            ║");
        System.out.println("║  6. Simular Corrida (Singleton + Observer)                        ║");
        System.out.println("║  7. Ver Classificacao (Singleton)                                 ║");
        System.out.println("║  8. Demonstracao Completa dos Padroes                             ║");
        System.out.println("║  0. Sair                                                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opcao: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void criarCarro() {
        System.out.println("\nCRIAR CARRO MANUALMENTE (Padrao Builder)");
        System.out.println("=".repeat(60));

        System.out.print("Nome do Piloto: ");
        String nomePiloto = scanner.nextLine();
        System.out.print("Numero do Piloto: ");
        int numero = lerOpcao();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        Piloto piloto = new Piloto(nomePiloto, numero, nacionalidade);

        System.out.print("Escuderia: ");
        String escuderia = scanner.nextLine();
        System.out.print("Motor: ");
        String motor = scanner.nextLine();
        System.out.print("Chassi: ");
        String chassi = scanner.nextLine();
        System.out.print("Potencia (HP): ");
        int potencia = lerOpcao();
        System.out.print("Aerodinamica (0-100): ");
        double aero = Double.parseDouble(scanner.nextLine());

        try {
            CarroF1 carro = new CarroF1Builder()
                    .comEscuderia(escuderia)
                    .comMotor(motor)
                    .comChassi(chassi)
                    .comPotencia(potencia)
                    .comAerodinamica(aero)
                    .comPiloto(piloto)
                    .build();

            campeonato.inscreverCarro(carro);
            System.out.println("\nCarro criado com sucesso!");
            System.out.println(carro);
        } catch (IllegalStateException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }

    private static void cadastrarPilotosAleatorios() {
        System.out.println("\nCADASTRAR PILOTOS ALEATORIOS - TEMPORADA 2025");
        System.out.println("=".repeat(60));

        int pilotosRestantes = PilotosF1_2025.getPilotosRestantes();
        System.out.println("Pilotos disponiveis: " + pilotosRestantes);

        if (pilotosRestantes == 0) {
            System.out.println("Todos os 21 pilotos ja foram cadastrados!");
            System.out.print("\nDeseja resetar e cadastrar novamente? (s/n): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                PilotosF1_2025.resetar();
                pilotosRestantes = 21;
                System.out.println("Lista resetada!");
            } else {
                return;
            }
        }

        // Sugerir quantidade baseada em pilotos disponíveis
        int sugestao = Math.min(7, pilotosRestantes);
        System.out.print("Quantos pilotos deseja cadastrar? (sugestao: " + sugestao + "): ");
        int quantidade = lerOpcao();

        if (quantidade <= 0) {
            System.out.println("Quantidade invalida!");
            return;
        }

        if (quantidade > pilotosRestantes) {
            System.out.println(
                    "Apenas " + pilotosRestantes + " pilotos disponiveis. Cadastrando " + pilotosRestantes + "...");
            quantidade = pilotosRestantes;
        }

        // Cadastrar pilotos aleatórios
        List<Piloto> pilotos = PilotosF1_2025.cadastrarPilotosAleatorios(quantidade);

        System.out.println("\n" + pilotos.size() + " pilotos cadastrados:");
        System.out.println("-".repeat(60));

        for (Piloto piloto : pilotos) {
            // Criar carro para o piloto baseado na equipe
            CarroF1 carro = criarCarroParaPiloto(piloto);
            campeonato.inscreverCarro(carro);
            System.out.println("  #" + piloto.getNumero() + " " + piloto.getNome() +
                    " (" + piloto.getEquipe() + ")");
        }

        System.out.println("\nPilotos restantes para cadastro: " + PilotosF1_2025.getPilotosRestantes());
    }

    private static CarroF1 criarCarroParaPiloto(Piloto piloto) {
        String equipe = piloto.getEquipe();
        int potencia = 970;
        double aero = 88.0;
        String motor = "Motor V6 Turbo";
        String chassi = "Chassi 2025";

        // Configurações específicas por equipe
        switch (equipe) {
            case "Red Bull Racing":
                potencia = 1000;
                aero = 95.5;
                motor = "Honda RBPT";
                chassi = "RB21";
                break;
            case "McLaren":
                potencia = 995;
                aero = 94.0;
                motor = "Mercedes M15";
                chassi = "MCL39";
                break;
            case "Ferrari":
                potencia = 990;
                aero = 93.0;
                motor = "Ferrari V6 Turbo";
                chassi = "SF-25";
                break;
            case "Mercedes":
                potencia = 985;
                aero = 92.0;
                motor = "Mercedes M15 E Performance";
                chassi = "W16";
                break;
            case "Aston Martin":
                potencia = 980;
                aero = 90.0;
                motor = "Mercedes M15";
                chassi = "AMR25";
                break;
            case "Alpine":
                potencia = 975;
                aero = 89.0;
                motor = "Renault E-Tech";
                chassi = "A525";
                break;
            case "Williams":
                potencia = 972;
                aero = 88.5;
                motor = "Mercedes M15";
                chassi = "FW47";
                break;
            case "RB":
                potencia = 970;
                aero = 88.0;
                motor = "Honda RBPT";
                chassi = "VCARB01";
                break;
            case "Haas":
                potencia = 968;
                aero = 87.5;
                motor = "Ferrari V6";
                chassi = "VF-25";
                break;
            case "Sauber":
                potencia = 965;
                aero = 87.0;
                motor = "Ferrari V6";
                chassi = "C45";
                break;
        }

        return new CarroF1Builder()
                .comEscuderia(equipe)
                .comMotor(motor)
                .comChassi(chassi)
                .comPotencia(potencia)
                .comAerodinamica(aero)
                .comPeso(798.0)
                .comPiloto(piloto)
                .build();
    }

    private static void definirEstrategia() {
        System.out.println("\nDEFINIR ESTRATEGIA DE CORRIDA (Padrao Strategy)");
        System.out.println("=".repeat(60));

        if (campeonato.getCarrosInscritos().isEmpty()) {
            System.out.println("Nenhum carro cadastrado! Crie um carro primeiro.");
            return;
        }

        System.out.println("Carros disponiveis:");
        List<CarroF1> carrosInscritos = campeonato.getCarrosInscritos();
        for (int i = 0; i < carrosInscritos.size(); i++) {
            System.out.println((i + 1) + ". " + carrosInscritos.get(i).getPiloto().getNome());
        }

        System.out.print("Escolha o piloto: ");
        int indicePiloto = lerOpcao() - 1;

        if (indicePiloto < 0 || indicePiloto >= carrosInscritos.size()) {
            System.out.println("Piloto invalido!");
            return;
        }

        Piloto pilotoSelecionado = carrosInscritos.get(indicePiloto).getPiloto();
        GerenciadorEstrategia gerenciadorEstrategia = new GerenciadorEstrategia(pilotoSelecionado.getNome());

        gerenciadorEstrategia.exibirEstrategiasDisponiveis();

        System.out.print("\nEscolha a estrategia: ");
        int opcaoEstrategia = lerOpcao();

        EstrategiaCorrida estrategiaSelecionada = null;
        switch (opcaoEstrategia) {
            case 1:
                estrategiaSelecionada = new EstrategiaUmaParada();
                break;
            case 2:
                estrategiaSelecionada = new EstrategiaDuasParadas();
                break;
            case 3:
                estrategiaSelecionada = new EstrategiaAgressiva();
                break;
            default:
                System.out.println("Opcao invalida!");
                return;
        }

        gerenciadorEstrategia.definirEstrategia(estrategiaSelecionada);
        gerenciadorEstrategia.executarEstrategia();
    }

    private static void visualizarTelemetria() {
        System.out.println("\nVISUALIZAR TELEMETRIA (Padrao Adapter)");
        System.out.println("=".repeat(60));

        if (campeonato.getCarrosInscritos().isEmpty()) {
            System.out.println("Nenhum carro cadastrado! Crie um carro primeiro.");
            return;
        }

        System.out.println("Carros disponiveis:");
        List<CarroF1> carrosDisponiveis = campeonato.getCarrosInscritos();
        for (int i = 0; i < carrosDisponiveis.size(); i++) {
            System.out.println((i + 1) + ". " + carrosDisponiveis.get(i).getEscuderia() + " - " +
                    carrosDisponiveis.get(i).getPiloto().getNome());
        }

        System.out.print("Escolha o carro: ");
        int indiceCarro = lerOpcao() - 1;

        if (indiceCarro < 0 || indiceCarro >= carrosDisponiveis.size()) {
            System.out.println("Carro invalido!");
            return;
        }

        CarroF1 carroSelecionado = carrosDisponiveis.get(indiceCarro);
        TelemetriaAdapter adaptadorTelemetria = new TelemetriaAdapter(carroSelecionado);

        System.out.println("\n" + adaptadorTelemetria.getTelemetriaCompleta());
    }

    private static void configurarObservadores() {
        System.out.println("\nCONFIGURAR OBSERVADORES (Padrao Observer)");
        System.out.println("=".repeat(60));

        System.out.println("1. Adicionar Observador de Equipe");
        System.out.println("2. Adicionar Observador de Midia");
        System.out.println("3. Adicionar Observador Torcedor");
        System.out.print("Escolha: ");

        int opcaoObservador = lerOpcao();

        switch (opcaoObservador) {
            case 1:
                System.out.print("Nome da Equipe: ");
                String nomeEquipe = scanner.nextLine();
                sistemaNotificacoes.adicionarObservador(new EquipeObserver(nomeEquipe));
                break;
            case 2:
                System.out.print("Nome do Canal: ");
                String nomeCanal = scanner.nextLine();
                sistemaNotificacoes.adicionarObservador(new MidiaObserver(nomeCanal));
                break;
            case 3:
                System.out.print("Piloto Favorito: ");
                String nomePilotoFavorito = scanner.nextLine();
                sistemaNotificacoes.adicionarObservador(new TorcedorObserver(nomePilotoFavorito));
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

    private static void simularCorrida() {
        System.out.println("\nSIMULAR CORRIDA (Padrao Singleton + Observer)");
        System.out.println("=".repeat(60));

        if (campeonato.getCarrosInscritos().isEmpty()) {
            System.out.println("Nenhum carro cadastrado! Crie carros primeiro.");
            return;
        }

        System.out.print("Nome da corrida (ex: GP do Brasil): ");
        String nomeCorrida = scanner.nextLine();

        // Notificar observadores sobre o início
        sistemaNotificacoes.notificarEvento("LARGADA: " + nomeCorrida + "!");

        // Simular corrida usando o Singleton
        campeonato.simularCorrida(nomeCorrida);

        // Notificar sobre o vencedor REAL da corrida
        CarroF1 vencedor = campeonato.getVencedorUltimaCorrida();
        if (vencedor != null) {
            String nomeVencedor = vencedor.getPiloto().getNome();
            sistemaNotificacoes.notificarEvento(nomeVencedor + " venceu o " + nomeCorrida + "!");
        }
    }

    private static void verClassificacao() {
        System.out.println("\nVER CLASSIFICACAO (Padrao Singleton)");
        System.out.println("=".repeat(60));

        campeonato.exibirClassificacaoCampeonato();
        campeonato.exibirHistoricoVencedores();
    }

    private static void demonstracaoCompleta() {
        System.out.println("\nDEMONSTRACAO COMPLETA DOS PADROES");
        System.out.println("=".repeat(70));
        System.out.println("Esta demonstracao mostrara todos os padroes em acao!");
        System.out.println();

        DemonstradorPadroes.executarDemonstracao();
    }

    private static void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
