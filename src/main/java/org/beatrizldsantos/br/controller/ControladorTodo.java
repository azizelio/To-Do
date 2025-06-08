package org.beatrizldsantos.br.controller;

import org.beatrizldsantos.br.model.Tarefa;
import org.beatrizldsantos.br.model.GerenciadorTarefas;
import org.beatrizldsantos.br.view.VisaoTodo;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControladorTodo implements GerenciadorTarefas.OuvinteMudancaTarefa, VisaoTodo.OuvintePainelTarefa {
    private final GerenciadorTarefas modelo;
    private final VisaoTodo view;

    public ControladorTodo(GerenciadorTarefas modelo, VisaoTodo visualizacao) {
        this.modelo = modelo;
        this.view = visualizacao;

        modelo.adicionarOuvinteMudancaTarefa(this);
    }


    public void inicializar() {
        view.inicializar();
        
        view.definirOuvinteAdicionarTarefa(this::tratarAdicionarTarefa);
        view.definirOuvinteLimparConcluidas(e -> modelo.limparTarefasConcluidas());
        
        
        modelo.carregarTarefas();
        
        view.mostrarJanela();
    }

    private void tratarAdicionarTarefa(ActionEvent e) {
        String texto = view.getTextoEntradaTarefa();
        if (!texto.isEmpty()) {
            modelo.adicionarTarefa(texto);
            view.limparEntradaTarefa();
        }
    }

    @Override
    public void aoMudarTarefas() {
        SwingUtilities.invokeLater(() -> {
            view.atualizarPainelTarefas(modelo.getTarefas(), this);
            view.atualizarRotuloStatus(modelo.getQuantidadeRestante());
        });
    }

    @Override
    public void aoMudarStatusTarefa(Tarefa tarefa, boolean concluida) {
        modelo.atualizarStatusTarefa(tarefa, concluida);
    }

    @Override
    public void aoDeletarTarefa(Tarefa tarefa) {
        modelo.removerTarefa(tarefa);
    }
}