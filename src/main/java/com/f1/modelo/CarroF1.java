package com.f1.modelo;

/**
 * Classe que representa um carro de Fórmula 1
 */
public class CarroF1 {
    private String escuderia;
    private String motor;
    private String chassi;
    private int potencia; // HP
    private double aerodinamica; // 0-100
    private double peso; // kg
    private String pneus;
    private Piloto piloto;

    public CarroF1() {
        this.pneus = "Medium";
        this.peso = 798.0; // Peso mínimo regulamentar F1
    }

    // Getters e Setters
    public String getEscuderia() {
        return escuderia;
    }

    public void setEscuderia(String escuderia) {
        this.escuderia = escuderia;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public double getAerodinamica() {
        return aerodinamica;
    }

    public void setAerodinamica(double aerodinamica) {
        this.aerodinamica = aerodinamica;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getPneus() {
        return pneus;
    }

    public void setPneus(String pneus) {
        this.pneus = pneus;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
        if (piloto != null) {
            piloto.setEquipe(this.escuderia);
        }
    }

    /**
     * Calcula o desempenho total do carro baseado em seus atributos
     * Fórmula: (Potência * 0.4) + (Aerodinâmica * 0.5) - (Peso * 0.1)
     */
    public double calcularDesempenho() {
        return (potencia * 0.4) + (aerodinamica * 0.5) - (peso * 0.1);
    }

    @Override
    public String toString() {
        return String.format(
            "╔════════════════════════════════════════╗\n" +
            "║             CARRO F1                   ║\n" +
            "╠════════════════════════════════════════╣\n" +
            "║ Escuderia: %-28s ║\n" +
            "║ Motor: %-32s ║\n" +
            "║ Chassi: %-31s ║\n" +
            "║ Potencia: %-22d HP ║\n" +
            "║ Aerodinamica: %-24.1f ║\n" +
            "║ Peso: %-28.1f kg ║\n" +
            "║ Pneus: %-31s ║\n" +
            "║ Piloto: %-30s ║\n" +
            "║ Desempenho Total: %-20.2f ║\n" +
            "╚════════════════════════════════════════╝",
            escuderia, motor, chassi, potencia, aerodinamica, peso, pneus,
            piloto != null ? piloto.getNome() : "Sem piloto",
            calcularDesempenho()
        );
    }
}
