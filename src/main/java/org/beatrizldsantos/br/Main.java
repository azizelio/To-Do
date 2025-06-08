package org.beatrizldsantos.br;

import org.beatrizldsantos.br.controller.ControladorTodo;
import org.beatrizldsantos.br.model.GerenciadorTarefas;
import org.beatrizldsantos.br.view.VisaoTodo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            GerenciadorTarefas modelo = new GerenciadorTarefas();
            VisaoTodo visao = new VisaoTodo();
            ControladorTodo controlador = new ControladorTodo(modelo, visao);

            controlador.inicializar();
        });
    }
}