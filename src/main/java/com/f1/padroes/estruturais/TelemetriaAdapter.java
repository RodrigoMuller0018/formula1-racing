package com.f1.padroes.estruturais;

import com.f1.modelo.CarroF1;

/**
 * Interface alvo que define os métodos esperados pelo sistema de telemetria
 */
interface SistemaTelemetria {
    String getModeloCarro();

    double getVelocidadeMaxima();

    double getTemperaturaMotor();

    String getStatusPneus();

    String getTelemetriaCompleta();
}

/**
 * Adapter que converte CarroF1 para o formato de SistemaTelemetria
 */
public class TelemetriaAdapter implements SistemaTelemetria {
    private CarroF1 carro; // Adaptee

    public TelemetriaAdapter(CarroF1 carro) {
        this.carro = carro;
    }

    @Override
    public String getModeloCarro() {
        return carro.getEscuderia() + " - " + carro.getChassi();
    }

    @Override
    public double getVelocidadeMaxima() {
        // Calcula velocidade baseada na potência
        // Fórmula simplificada: potência / 3 (aproximação)
        return carro.getPotencia() / 3.0;
    }

    @Override
    public double getTemperaturaMotor() {
        // Simula temperatura baseada na potência
        // Quanto mais potente, mais quente
        return 90.0 + (carro.getPotencia() - 900) * 0.05;
    }

    @Override
    public String getStatusPneus() {
        return carro.getPneus();
    }

    @Override
    public String getTelemetriaCompleta() {
        return String.format(
                "╔══════════ TELEMETRIA F1 ══════════╗\n" +
                "║ Modelo: %-25s                     \n" +
                "║ Piloto: %-25s                     \n" +
                "║ Velocidade Máx: %-13.0f km/h      \n" +
                "║ Temperatura Motor: %-10.1f °C     \n" +
                "║ Status Pneus: %-18s               \n" +
                "║ Potência: %-20d HP                \n" +
                "║ Aerodinâmica: %-20.1f             \n" +
                "║ Peso: %-24.1f kg                  \n" +
                "╚═══════════════════════════════════╝",
        getModeloCarro(),
        carro.getPiloto() != null ? carro.getPiloto().getNome() : "N/A",
        getVelocidadeMaxima(),
        getTemperaturaMotor(),
        getStatusPneus(),
        carro.getPotencia(),
        carro.getAerodinamica(),
        carro.getPeso());
    }
}
