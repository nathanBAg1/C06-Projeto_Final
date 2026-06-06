package br.faculdade.utils.acesso;

import br.faculdade.exceptions.UsuarioVazioException;
import br.faculdade.usuarios.Supervisor;
import br.faculdade.utils.login.LoginSupervisor;
import br.faculdade.utils.telas.TelaSupervisor;

public  class AcessoSupervisor {

    public static void acessoSupervisor(){

        Supervisor supervisor = null;

        System.out.println("Bem Vindo Supervisor");

        supervisor = LoginSupervisor.loginSupervisor();

        try {
            if (supervisor != null) {
                TelaSupervisor.telaSupervisor(supervisor);
            } else {
                throw new UsuarioVazioException("Não foi possível completar seu acesso.");
            }
        } catch (UsuarioVazioException e) {
            System.out.println(e.getMessage());
        }

    }

}