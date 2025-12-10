package com.f1.padroes.comportamentais;

import java.util.ArrayList;
import java.util.List;

/**
 * PADRÃO OBSERVER - Comportamental (BÔNUS)
 *
 * Propósito: Definir uma dependência um-para-muitos entre objetos, de modo que,
 * quando um objeto muda de estado, todos os seus dependentes são notificados.
 *
 * Aplicação: Sistema de notificações de corrida
 *
 * Justificativa: Durante uma corrida, vários interessados (equipes, mídia,
 * torcedores) precisam ser notificados de eventos importantes (pit stops,
 * ultrapassagens, incidentes). O Observer permite adicionar/remover
 * observadores
 * dinamicamente sem modificar o código da corrida.
 */

// Subject (Observable)
public class SistemaNotificacoes {
    private List<EventoObserver> observadores = new ArrayList<>();
    private String ultimoEvento;

    public void adicionarObservador(EventoObserver observador) {
        observadores.add(observador);
        System.out.println("Observador registrado: " + observador.getNome());
    }

    public void removerObservador(EventoObserver observador) {
        observadores.remove(observador);
        System.out.println("Observador removido: " + observador.getNome());
    }

    public void notificarEvento(String evento) {
        this.ultimoEvento = evento;
        System.out.println("\nEVENTO: " + evento);
        System.out.println("Notificando " + observadores.size() + " observador(es)...\n");

        for (EventoObserver observador : observadores) {
            observador.atualizar(evento);
        }
    }

    public String getUltimoEvento() {
        return ultimoEvento;
    }
}

