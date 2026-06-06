package br.faculdade.utils.login;

import br.faculdade.dao.SupervisorDao;
import br.faculdade.usuarios.Supervisor;

import static br.faculdade.Main.sc;

public  class LoginSupervisor {

    public static Supervisor loginSupervisor()
    {
        String loginCPF;
        String loginEmail;
        Supervisor supervisor = null;
        SupervisorDao supervisorDao = new SupervisorDao();

        sc.nextLine();

        System.out.println("Insira o seu email: ");
        loginEmail = sc .nextLine();

        System.out.println("Insira o seu CPF: ");
        loginCPF = sc.nextLine();

        supervisor = supervisorDao.autentica(loginCPF,loginEmail);

        return supervisor ;
    }
}