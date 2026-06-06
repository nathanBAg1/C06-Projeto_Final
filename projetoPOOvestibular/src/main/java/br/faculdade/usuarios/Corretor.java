package br.faculdade.usuarios;
import br.faculdade.dao.VestibulandoPrestaVestibularDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Corretor extends Pessoa {

    public Corretor(String cpf, String nome, String email) {
        super(cpf, nome, email);
    }

    @Override
    public void confereSeusDadosEspecificos() {
        System.out.println("Funcao : Corretor");
    }

    @Override
    public void atualizaEmail(String novoEmail) {
        this.email = novoEmail;
    }

    // lanca as notas lendo de um arquivo (cpf ; id_vestibular ; nota)
    // usa pool de threads: cada nota e um UPDATE independente (lote I/O-bound)
    public void lancarNotasPorArquivo(String caminhoArquivo) {

        // 1) leitura do arquivo: sequencial (rapida e evita concorrencia na leitura)
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] campos = linha.split(";");           // cpf ; id_vestibular ; nota
                if (campos.length < 3) {
                    System.out.println("Linha invalida (esperado cpf;id_vestibular;nota): " + linha);
                    continue;
                }
                registros.add(campos);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // 2) lancamento em paralelo: pool limitado p/ nao estourar o max_connections do MySQL
        ExecutorService pool = Executors.newFixedThreadPool(8);
        for (String[] campos : registros) {
            pool.submit(() -> {
                try {
                    String cpf = campos[0].trim();
                    int idVestibular = Integer.parseInt(campos[1].trim());
                    int nota = Integer.parseInt(campos[2].trim());

                    // cada task usa o PROPRIO DAO (conexao propria) -> thread-safe
                    VestibulandoPrestaVestibularDAO dao = new VestibulandoPrestaVestibularDAO();
                    if (dao.lancarNota(cpf, idVestibular, nota))
                        System.out.println("Nota " + nota + " lancada para " + cpf);
                    else
                        System.out.println("Falha ao lancar nota de " + cpf);
                } catch (NumberFormatException e) {
                    System.out.println("Linha com numero invalido: " + e.getMessage());
                }
            });
        }

        // 3) encerra o pool e espera todas as notas terminarem
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Lancamento de notas interrompido.");
        }

        System.out.println("Lancamento de notas finalizado.");
    }
}