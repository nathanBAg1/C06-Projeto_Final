package br.faculdade.utils.login;

import br.faculdade.curso.vestibular.Vestibular;
import br.faculdade.dao.MateriaDAO;
import br.faculdade.dao.VestibulandoDAO;
import br.faculdade.dao.VestibulandoPrestaVestibularDAO;
import br.faculdade.dao.VestibularDAO;
import br.faculdade.usuarios.Vestibulando;

import java.util.List;

import static br.faculdade.Main.sc;

public class LoginVestibulando {

    public static Vestibulando loginVestibulando(){

        String loginCPF;

        Vestibulando vestibulando = null;

        System.out.println("Insira o seu CPF: ");


        try {
            sc.nextLine();
            loginCPF = sc.nextLine();

            VestibulandoDAO retornandoVestibulando = new VestibulandoDAO();
            VestibulandoPrestaVestibularDAO vestibulandoPrestaVestibularDAO = new VestibulandoPrestaVestibularDAO();
            VestibularDAO vestibularDAO = new VestibularDAO();
            MateriaDAO materiaDAO = new MateriaDAO();

            vestibulando = retornandoVestibulando.selectVestibulando(loginCPF); // Pegando os dados gerais do vestibulando

            // Recuperando os vestibulares em que o vestibulando está escrito

            List<Integer> ids_vestibulares =
                    vestibulandoPrestaVestibularDAO.selectIdVestibularesPrestados(loginCPF); // Lista de IDS dos Vestibulares
            for(int id : ids_vestibulares ) {
                Vestibular vestibular = vestibularDAO.selectVestibular(id);
                vestibular.setResultado(vestibulandoPrestaVestibularDAO.selectNota(loginCPF, id));

                vestibulando.addVestibular(vestibular); // Adicionando os vestibulares em Vestibulando
                vestibular.setMaterias(materiaDAO.selectMaterias(vestibular.getId())); // Adicionando Matérias Para o Vestibular
            }

        } catch (NullPointerException e) {
            System.out.println("Erro: " + e);
        }

        return vestibulando;
    }

}