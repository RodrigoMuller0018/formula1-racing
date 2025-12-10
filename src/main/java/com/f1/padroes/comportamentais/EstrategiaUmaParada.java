package com.f1.padroes.comportamentais;

/**
 * Estratégia concreta: Uma Parada
 * Conservadora, economiza combustível e pneus
 */
public class EstrategiaUmaParada implements EstrategiaCorrida {

    @Override
    public String executar() {
        StringBuilder sb = new StringBuilder();
        sb.append("ESTRATÉGIA: UMA PARADA\n\n");
        sb.append("Volta 1-25: Ritmo conservador, gerenciar pneus Medium\n");
        sb.append("Volta 26: PIT STOP - Trocar para pneus Hard\n");
        sb.append("Volta 27-50: Manter ritmo constante até o final\n\n");
        sb.append("Vantagens:\n");
        sb.append("  ✓ Menos tempo perdido no pit lane\n");
        sb.append("  ✓ Menor consumo de combustível\n");
        sb.append("  ✓ Menor desgaste de pneus\n\n");
        sb.append("Desvantagens:\n");
        sb.append("  ✗ Pneus podem degradar no final\n");
        sb.append("  ✗ Menor ritmo em comparação com duas paradas\n");
        return sb.toString();
    }

    @Override
    public String getNome() {
        return "Uma Parada";
    }

    @Override
    public double calcularBonusDesempenho() {
        return 5.0; // Bônus por economia de tempo no pit
    }
}
