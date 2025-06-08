package org.beatrizldsantos.br.view;

import org.beatrizldsantos.br.model.Tarefa;
import org.beatrizldsantos.br.view.components.PainelStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class VisaoTodo {
    private JFrame janela;
    private JPanel painelPrincipal;
    private JTextField campoEntradaTarefa;
    private JPanel painelTarefas;
    private JButton botaoAdicionar;
    private JButton botaoLimparConcluidas;
    private PainelStatus painelStatus;

    public void inicializar() {
        // Criar janela principal
        janela = new JFrame("Aplicativo de Tarefas");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 700);
        janela.setMinimumSize(new Dimension(500, 400));

        // Painel principal com BoxLayout
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(new EmptyBorder(30, 30, 30, 30));
        painelPrincipal.setBackground(new Color(245, 245, 245));

        // Adicionar componentes
        painelPrincipal.add(criarPainelCabecalho());
        painelPrincipal.add(criarPainelEntrada());
        painelPrincipal.add(criarPainelConteinerTarefas());
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Painel de status
        painelStatus = new PainelStatus();
        botaoLimparConcluidas = painelStatus.getBotaoLimparConcluidas();
        painelPrincipal.add(painelStatus);

        // Adicionar painel principal à janela
        janela.add(painelPrincipal);
    }

    private JPanel criarPainelCabecalho() {
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new BoxLayout(painelCabecalho, BoxLayout.Y_AXIS));
        painelCabecalho.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCabecalho.setBackground(new Color(245, 245, 245));
        painelCabecalho.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel rotuloTitulo = new JLabel("To-Do List");
        rotuloTitulo.setFont(new Font("Dark Grotesque", Font.PLAIN, 36));
        rotuloTitulo.setForeground(new Color(50, 50, 50));
        rotuloTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCabecalho.add(rotuloTitulo);
        painelCabecalho.add(Box.createRigidArea(new Dimension(0, 20)));
        
        return painelCabecalho;
    }


    private JPanel criarPainelEntrada() {
        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new BoxLayout(painelEntrada, BoxLayout.X_AXIS));
        painelEntrada.setMaximumSize(new Dimension(500, 50));
        painelEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelEntrada.setBackground(new Color(245, 245, 245));
        painelEntrada.setBorder(new EmptyBorder(0, 0, 20, 0));

        campoEntradaTarefa = new JTextField();
        campoEntradaTarefa.setFont(new Font("Dark Grotesque", Font.PLAIN, 16));
        campoEntradaTarefa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 0), 1, true),
                BorderFactory.createEmptyBorder(10, 15, 0, 15)));

        botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setFont(new Font("Dark Grotesque", Font.PLAIN, 14));
        botaoAdicionar.setBackground(new Color(70, 130, 180));
        botaoAdicionar.setForeground(Color.BLACK);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setBorder(new EmptyBorder(10, 20, 10, 20));
        botaoAdicionar.setPreferredSize(new Dimension(100, 40));
        botaoAdicionar.setMaximumSize(new Dimension(100, 40));

        painelEntrada.add(campoEntradaTarefa);
        painelEntrada.add(Box.createRigidArea(new Dimension(10, 0)));
        painelEntrada.add(botaoAdicionar);
        
        return painelEntrada;
    }

    private JPanel criarPainelConteinerTarefas() {
        JPanel painelConteinerTarefas = new JPanel();
        painelConteinerTarefas.setLayout(new BoxLayout(painelConteinerTarefas, BoxLayout.Y_AXIS));
        painelConteinerTarefas.setBackground(new Color(255, 255, 255));
        painelConteinerTarefas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel de tarefas
        painelTarefas = new JPanel();
        painelTarefas.setLayout(new BoxLayout(painelTarefas, BoxLayout.Y_AXIS));
        painelTarefas.setBackground(new Color(255, 255, 255));
        painelTarefas.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane painelRolagem = new JScrollPane(painelTarefas);
        painelRolagem.setBorder(null);
        painelRolagem.setPreferredSize(new Dimension(500, 400));
        painelRolagem.setMaximumSize(new Dimension(500, 400));
        painelRolagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelRolagem.getVerticalScrollBar().setUnitIncrement(16);

        painelConteinerTarefas.add(painelRolagem);
        
        return painelConteinerTarefas;
    }

    public void atualizarPainelTarefas(List<Tarefa> tarefas, OuvintePainelTarefa ouvinte) {
        painelTarefas.removeAll();

        for (Tarefa tarefa : tarefas) {
            PainelTarefa painelTarefa = new PainelTarefa(tarefa, ouvinte);
            painelTarefas.add(painelTarefa);
            painelTarefas.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        painelTarefas.revalidate();
        painelTarefas.repaint();
    }

    public void atualizarRotuloStatus(int quantidadeRestante) {
        painelStatus.atualizarQuantidadeRestante(quantidadeRestante);
    }

    public void definirOuvinteAdicionarTarefa(ActionListener ouvinte) {
        botaoAdicionar.addActionListener(ouvinte);
        campoEntradaTarefa.addActionListener(ouvinte);
    }

    public void definirOuvinteLimparConcluidas(ActionListener ouvinte) {
        botaoLimparConcluidas.addActionListener(ouvinte);
    }

    public String getTextoEntradaTarefa() {
        return campoEntradaTarefa.getText().trim();
    }

    public void limparEntradaTarefa() {
        campoEntradaTarefa.setText("");
    }

    public void mostrarJanela() {
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public JFrame getJanela() {
        return janela;
    }

    public interface OuvintePainelTarefa {
        void aoMudarStatusTarefa(Tarefa tarefa, boolean concluida);
        void aoDeletarTarefa(Tarefa tarefa);
    }
}