package com.f1.padroes.criacionais;

import com.f1.modelo.CarroF1;
import com.f1.modelo.Piloto;

/**
 * Builder para construção de objetos CarroF1
 */
public class CarroF1Builder {
    private CarroF1 carro;

    public CarroF1Builder() {
        this.carro = new CarroF1();
    }

    /**
     * Define a escuderia do carro
     *
     * @return this para permitir method chaining
     */
    public CarroF1Builder comEscuderia(String escuderia) {
        carro.setEscuderia(escuderia);
        return this;
    }

    public CarroF1Builder comMotor(String motor) {
        carro.setMotor(motor);
        return this;
    }

    public CarroF1Builder comChassi(String chassi) {
        carro.setChassi(chassi);
        return this;
    }

    public CarroF1Builder comPotencia(int potencia) {
        carro.setPotencia(potencia);
        return this;
    }

    public CarroF1Builder comAerodinamica(double aerodinamica) {
        carro.setAerodinamica(aerodinamica);
        return this;
    }

    public CarroF1Builder comPeso(double peso) {
        carro.setPeso(peso);
        return this;
    }

    public CarroF1Builder comPneus(String pneus) {
        carro.setPneus(pneus);
        return this;
    }

    public CarroF1Builder comPiloto(Piloto piloto) {
        carro.setPiloto(piloto);
        return this;
    }

    /**
     * Finaliza a construção e retorna o carro
     * Valida se os campos obrigatórios foram preenchidos
     */
    public CarroF1 build() {
        if (carro.getEscuderia() == null || carro.getMotor() == null) {
            throw new IllegalStateException(
                    "Escuderia e Motor são obrigatórios para construir o carro!");
        }
        if (carro.getPiloto() == null) {
            throw new IllegalStateException(
                    "Um piloto deve ser atribuído ao carro!");
        }
        return carro;
    }
}
