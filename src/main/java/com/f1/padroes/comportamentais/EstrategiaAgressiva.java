package com.f1.padroes.comportamentais;

/**
 * Estratégia concreta: Agressiva
 * Ataque total desde o início, alta performance mas arriscada
 */
public class EstrategiaAgressiva implements EstrategiaCorrida {

    @Override
    public String executar() {
        StringBuilder sb = new StringBuilder();
        sb.append("ESTRATÉGIA: AGRESSIVA - ATAQUE TOTAL\n\n");
        sb.append("Volta 1-15: RITMO MÁXIMO com pneus Soft\n");
        sb.append("Volta 16: PIT STOP RÁPIDO - Trocar para pneus Soft novos\n");
        sb.append("Volta 17-32: CONTINUAR ATACANDO\n");
        sb.append("Volta 33: PIT STOP 2 - Trocar para pneus Medium\n");
        sb.append("Volta 34-50: Gerenciar vantagem ou continuar atacando\n\n");
        sb.append("Vantagens:\n");
        sb.append("  ✓ Ritmo máximo desde o início\n");
        sb.append("  ✓ Pressiona adversários\n");
        sb.append("  ✓ Pode criar gap importante\n\n");
        sb.append("Desvantagens:\n");
        sb.append("  ✗ Alto desgaste de pneus\n");
        sb.append("  ✗ Maior consumo de combustível\n");
        sb.append("  ✗ Risco de não completar a corrida\n");
        return sb.toString();
    }

    @Override
    public String getNome() {
        return "Agressiva";
    }

    @Override
    public double calcularBonusDesempenho() {
        return 8.0; // Alto bônus por ritmo agressivo, mas com riscos
    }
}
