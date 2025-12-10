package com.f1.padroes.comportamentais;

/**
 * Estratégia concreta: Duas Paradas
 * Balanceada, permite usar pneus frescos em diferentes stints
 */
public class EstrategiaDuasParadas implements EstrategiaCorrida {

    @Override
    public String executar() {
        StringBuilder sb = new StringBuilder();
        sb.append("ESTRATÉGIA: DUAS PARADAS\n\n");
        sb.append("Volta 1-18: Ritmo forte com pneus Soft\n");
        sb.append("Volta 19: PIT STOP 1 - Trocar para pneus Medium\n");
        sb.append("Volta 20-37: Ritmo consistente\n");
        sb.append("Volta 38: PIT STOP 2 - Trocar para pneus Soft\n");
        sb.append("Volta 39-50: Ataque final com pneus frescos\n\n");
        sb.append("Vantagens:\n");
        sb.append("  ✓ Pneus sempre frescos\n");
        sb.append("  ✓ Maior ritmo médio\n");
        sb.append("  ✓ Flexibilidade tática\n\n");
        sb.append("Desvantagens:\n");
        sb.append("  ✗ Tempo perdido em dois pit stops\n");
        sb.append("  ✗ Risco de tráfego ao sair do pit\n");
        return sb.toString();
    }

    @Override
    public String getNome() {
        return "Duas Paradas";
    }

    @Override
    public double calcularBonusDesempenho() {
        return 3.0; // Bônus moderado por pneus frescos
    }
}
