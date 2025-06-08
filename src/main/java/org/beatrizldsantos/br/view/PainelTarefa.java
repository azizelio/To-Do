package org.beatrizldsantos.br.view;

import org.beatrizldsantos.br.model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelTarefa extends JPanel {
    private final Tarefa tarefa;
    private final JCheckBox caixaSelecao;
    private final JLabel rotuloTexto;
    private final JButton botaoDeletar;

    public PainelTarefa(Tarefa tarefa, VisaoTodo.OuvintePainelTarefa ouvinte) {
        this.tarefa = tarefa;

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(500, 60));
        setPreferredSize(new Dimension(500, 60));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        setBackground(Color.WHITE);

        caixaSelecao = new JCheckBox();
        caixaSelecao.setSelected(tarefa.estaConcluida());
        caixaSelecao.setBackground(Color.WHITE);
        caixaSelecao.setPreferredSize(new Dimension(30, 30));

        rotuloTexto = new JLabel(tarefa.getTexto());
        rotuloTexto.setFont(new Font("Dark Grotesque", Font.PLAIN, 16));
        atualizarRotuloTexto();

        botaoDeletar = new JButton("×");
        botaoDeletar.setFont(new Font("Dark Grotesque", Font.PLAIN, 20));
        botaoDeletar.setBorderPainted(false);
        botaoDeletar.setContentAreaFilled(false);
        botaoDeletar.setFocusPainted(false);
        botaoDeletar.setForeground(new Color(200, 0, 0));
        botaoDeletar.setPreferredSize(new Dimension(30, 30));
        botaoDeletar.setVisible(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(250, 250, 250));
                botaoDeletar.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
                botaoDeletar.setVisible(false);
            }
        });

        caixaSelecao.addActionListener(e -> {
            boolean estaConcluida = caixaSelecao.isSelected();
            ouvinte.aoMudarStatusTarefa(tarefa, estaConcluida);
            atualizarRotuloTexto();
        });

        botaoDeletar.addActionListener(e -> ouvinte.aoDeletarTarefa(tarefa));

        JPanel painelEsquerdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelEsquerdo.setBackground(Color.WHITE);
        painelEsquerdo.add(caixaSelecao);
        painelEsquerdo.add(rotuloTexto);

        add(painelEsquerdo, BorderLayout.WEST);
        add(botaoDeletar, BorderLayout.EAST);
    }

    private void atualizarRotuloTexto() {
        if (tarefa.estaConcluida()) {
            rotuloTexto.setForeground(Color.GRAY);
            rotuloTexto.setText("<html><strike>" + tarefa.getTexto() + "</strike></html>");
        } else {
            rotuloTexto.setForeground(Color.BLACK);
            rotuloTexto.setText(tarefa.getTexto());
        }
    }
}