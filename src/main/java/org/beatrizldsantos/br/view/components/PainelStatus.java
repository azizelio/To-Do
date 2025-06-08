package org.beatrizldsantos.br.view.components;

import javax.swing.*;
import java.awt.*;

public class PainelStatus extends JPanel {
    private final JLabel rotuloStatus;
    private final JButton botaoLimparConcluidas;

    public PainelStatus() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(new Dimension(500, 40));
        setPreferredSize(new Dimension(500, 40));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        rotuloStatus = new JLabel("0 itens restantes");
        rotuloStatus.setFont(new Font("Dark Grotesque", Font.PLAIN, 14));
        rotuloStatus.setForeground(new Color(100, 100, 100));

        botaoLimparConcluidas = new JButton("Limpar concluídas");
        botaoLimparConcluidas.setFont(new Font("Dark Grotesque", Font.PLAIN, 14));
        botaoLimparConcluidas.setBorderPainted(false);
        botaoLimparConcluidas.setContentAreaFilled(false);
        botaoLimparConcluidas.setFocusPainted(false);
        botaoLimparConcluidas.setForeground(new Color(100, 100, 100));


        add(rotuloStatus);
        add(Box.createHorizontalGlue());
        add(botaoLimparConcluidas);
    }

    public void atualizarQuantidadeRestante(int quantidadeRestante) {
        rotuloStatus.setText(quantidadeRestante + (quantidadeRestante == 1 ? " item restante" : " itens restantes"));
    }

    public JButton getBotaoLimparConcluidas() {
        return botaoLimparConcluidas;
    }
}