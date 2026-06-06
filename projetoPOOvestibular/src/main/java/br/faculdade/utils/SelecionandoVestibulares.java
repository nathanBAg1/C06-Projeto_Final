package br.faculdade.utils;

import br.faculdade.curso.Curso;
import br.faculdade.curso.vestibular.Vestibular;
import br.faculdade.dao.CursoDAO;
import br.faculdade.dao.CursoOfereceVestibularDAO;
import br.faculdade.dao.VestibulandoPrestaVestibularDAO;
import br.faculdade.exceptions.FalhaSelecaoVestibularException;
import br.faculdade.exceptions.OpcaoInvalidaException;
import br.faculdade.usuarios.Vestibulando;

import java.util.*;

import static br.faculdade.Main.sc;

public class SelecionandoVestibulares {

    public static void selecionandoVestibulares(Vestibulando vestibulando) {
        CursoDAO cursoDAO = new CursoDAO();
        CursoOfereceVestibularDAO cursoOfereceVestibularDAO = new CursoOfereceVestibularDAO();
        VestibulandoPrestaVestibularDAO vestibulandoPrestaVestibularDAO =
                new VestibulandoPrestaVestibularDAO();

        Map<Integer, Curso> cursosDisponiveis = cursoDAO.selectTodosCursos(); // Armazena os cursos disponíveis
        int opcao = -1;

        while(opcao != 1) {

            try {
                System.out.println("Para qual curso você pretende prestar vestibular?");
                // Mostra os cursos disponíveis
                cursosDisponiveis.forEach((index, curso) -> {
                    System.out.println(index + " - " + curso.getNome());
                });

                opcao = sc.nextInt();
                if (opcao <= 0 || opcao > cursosDisponiveis.size()) {
                    throw new OpcaoInvalidaException("Opção Inválida");
                }

                System.out.println("Inscrevendo vestibulando para o vestibular do curso escolhido.");

                try {
                    Vestibular escolhido =
                            cursoOfereceVestibularDAO.selectCursoOfereceVestibular(cursosDisponiveis.get(opcao).getId());

                    if(escolhido != null) {
                        vestibulandoPrestaVestibularDAO.insertVestibulandoPrestaVestibular(vestibulando, escolhido);
                        vestibulando.addVestibular(escolhido);
                    } else {
                        throw new FalhaSelecaoVestibularException("Falha na inscrição do vestibular para este curso.");
                    }
                } catch (FalhaSelecaoVestibularException e) {
                    System.out.println(e.getMessage());
                }

                opcao = 1;

            } catch (InputMismatchException | NullPointerException | OpcaoInvalidaException e) {
                System.out.println("Opção inválida.");
                opcao = -1;
                sc.nextLine();
            }
        }

    }

}