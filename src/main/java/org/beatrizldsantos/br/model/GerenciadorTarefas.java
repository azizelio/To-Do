package org.beatrizldsantos.br.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefas {
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private final File arquivoSalvamento = new File("tarefas.dat");
    private final List<OuvinteMudancaTarefa> ouvintes = new ArrayList<>();

    public interface OuvinteMudancaTarefa {
        void aoMudarTarefas();
    }

    public void adicionarOuvinteMudancaTarefa(OuvinteMudancaTarefa ouvinte) {
        ouvintes.add(ouvinte);
    }

    private void notificarOuvintes() {
        for (OuvinteMudancaTarefa ouvinte : ouvintes) {
            ouvinte.aoMudarTarefas();
        }
    }

    public void adicionarTarefa(String texto) {
        if (!texto.trim().isEmpty()) {
            Tarefa tarefa = new Tarefa(texto);
            tarefas.add(tarefa);
            notificarOuvintes();
            salvarTarefas();
        }
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
        notificarOuvintes();
        salvarTarefas();
    }

    public void atualizarStatusTarefa(Tarefa tarefa, boolean concluida) {
        tarefa.setConcluida(concluida);
        notificarOuvintes();
        salvarTarefas();
    }

    public void limparTarefasConcluidas() {
        ArrayList<Tarefa> tarefasParaRemover = new ArrayList<>();
        for (Tarefa tarefa : tarefas) {
            if (tarefa.estaConcluida()) {
                tarefasParaRemover.add(tarefa);
            }
        }
        tarefas.removeAll(tarefasParaRemover);
        notificarOuvintes();
        salvarTarefas();
    }

    public List<Tarefa> getTarefas() {
        return new ArrayList<>(tarefas);
    }

    public int getQuantidadeRestante() {
        int contador = 0;
        for (Tarefa tarefa : tarefas) {
            if (!tarefa.estaConcluida()) {
                contador++;
            }
        }
        return contador;
    }

    public void salvarTarefas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoSalvamento))) {
            oos.writeObject(tarefas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarTarefas() {
        if (arquivoSalvamento.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoSalvamento))) {
                tarefas = (ArrayList<Tarefa>) ois.readObject();
                notificarOuvintes();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}