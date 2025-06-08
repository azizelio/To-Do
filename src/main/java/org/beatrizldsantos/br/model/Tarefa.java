package org.beatrizldsantos.br.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String texto;
    private boolean concluida;
    private LocalDateTime criadaEm;

    public Tarefa(String texto) {
        this.texto = texto;
        this.concluida = false;
        this.criadaEm = LocalDateTime.now();
    }

    public String getTexto() {
        return texto;
    }

    public boolean estaConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDateTime getCriadaEm() {
        return criadaEm;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return criadaEm.format(formatador);
    }
}